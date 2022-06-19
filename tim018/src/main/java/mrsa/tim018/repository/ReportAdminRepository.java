package mrsa.tim018.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Report;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.utils.TimeUtils;

@Service
@Transactional
public class ReportAdminRepository {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private final String queryBegining = " SELECT new mrsa.tim018.model.Report(date_trunc('%s', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id)) "
			+ " FROM Reservation r "
			+ " WHERE r.isDeleted = FALSE ";
	private final String completedPart = " AND r.timeRange.fromDateTime < now() AND r.status != 1 ";
	private final String groupByPart = " GROUP BY date_trunc('%s', r.timeRange.fromDateTime) ";
	
	
	public List<Report> getReports(String period, String assetType, String fromDate, String toDate) {
		List<Reservation> reservations = reservationRepository.findAll();
		
		reservations = FilterByDate(fromDate, toDate, reservations);
		reservations = FilterByAssetType(assetType, reservations);
		
		Map<String, List<Reservation>> resetvationsInTimePeriod = FilterByPeriod(period, reservations);
		return createReports(resetvationsInTimePeriod);
	}
	
	private List<Report> createReports(Map<String, List<Reservation>> resetvationsInTimePeriod) {
		List<Report> reports = new ArrayList<Report>();
		for (String timePeriod : resetvationsInTimePeriod.keySet()) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date timeStamp = Date.from(getTimeStamp(timePeriod).atStartOfDay(defaultZoneId).toInstant());
			double income = calculateIncome(resetvationsInTimePeriod.get(timePeriod));
			long numberOfReservations = resetvationsInTimePeriod.get(timePeriod).size();
			reports.add(new Report(timeStamp, income, numberOfReservations));
		}
		return reports;
	}
	
	@SuppressWarnings("deprecation")
	private LocalDate getTimeStamp(String date) {
		String[] data = date.split(":");
		if (data.length==1) {
			return LocalDate.of(Integer.parseInt(date), 1, 1);
		} else if (data.length==2) {
			return LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 1);
		} else {
			return LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
		}
	}

	private double calculateIncome(List<Reservation> reservations) {
		double income = 0;
		for (Reservation reservation : reservations) {
			double priceWithDiscount = reservation.getTotalPrice() * (100-reservation.getLoyaltyState().getClientDiscountPercent())/100;
			double tax = priceWithDiscount * reservation.getLoyaltyState().getTaxPercent()/100;
			double reducedTax = tax * (100-reservation.getLoyaltyState().getRenterDiscountPercent())/100;
			income = income + reducedTax;
		}
		return (int) income;
	}

	private List<Reservation> FilterByDate(String fromDate, String toDate, List<Reservation> reservations) {
		if (!fromDate.equals("none")) {
			LocalDateTime startDate = TimeUtils.getLocalDate(fromDate).atStartOfDay();
			reservations = reservations
					  .stream()
					  .filter(r -> r.getTimeRange().getFromDateTime().isAfter(startDate))
					  .collect(Collectors.toList());
		}
		if (!toDate.equals("none")) {
			LocalDateTime endDate = TimeUtils.getLocalDate(toDate).atStartOfDay();
			reservations = reservations
					  .stream()
					  .filter(r -> r.getTimeRange().getFromDateTime().isBefore(endDate))
					  .collect(Collectors.toList());
		}
		return reservations;
	}
		 
	
	private List<Reservation> FilterByAssetType(String assetType, List<Reservation> reservations) {
		if (assetType.equals("boats")) {
			reservations = reservations
					  .stream()
					  .filter(r -> r.getAsset().getAssetType().equals(AssetType.BOAT))
					  .collect(Collectors.toList());
		} else if (assetType.equals("resorts")) {
			reservations = reservations
					  .stream()
					  .filter(r -> r.getAsset().getAssetType().equals(AssetType.RESORT))
					  .collect(Collectors.toList());
		} else if (assetType.equals("adventures")) {
			reservations = reservations
					  .stream()
					  .filter(r -> r.getAsset().getAssetType().equals(AssetType.FISHING_ADVENTURE))
					  .collect(Collectors.toList());
		}
		return reservations;
	}
	
	private Map<String, List<Reservation>> FilterByPeriod(String period, List<Reservation> reservations) {
		switch (period) {
		case "year":
			return getDataSplitByYears(reservations);
		case "month":
			return getDataSplitByMonth(reservations);
		case "week":
			return getDataSplitByWeek(reservations);
		default:
			return null;
		}
	}

	private Map<String, List<Reservation>> getDataSplitByWeek(List<Reservation> reservations) {
		Map<String, List<Reservation>> retData = new HashMap<String, List<Reservation>>();
		for (Reservation reservation : reservations) {
			int year = reservation.getTimeRange().getFromDateTime().getYear();
			int month = reservation.getTimeRange().getFromDateTime().getMonthValue();
			int day = reservation.getTimeRange().getFromDateTime().getDayOfMonth();
			String date = String.valueOf(year) + ":" + String.valueOf(month)+ ":" + String.valueOf(day);
			if (retData.containsKey(date)) {
				List<Reservation> yearsReservations = retData.get(date);
				yearsReservations.add(reservation);
				retData.put(date, yearsReservations);
			} else {
				List<Reservation> yearsReservations = new ArrayList<>();
				yearsReservations.add(reservation);
				retData.put(date, yearsReservations);
			}
		}
		return retData;
	}

	private Map<String, List<Reservation>> getDataSplitByMonth(List<Reservation> reservations) {
		Map<String, List<Reservation>> retData = new HashMap<String, List<Reservation>>();
		for (Reservation reservation : reservations) {
			int year = reservation.getTimeRange().getFromDateTime().getYear();
			int month = reservation.getTimeRange().getFromDateTime().getMonthValue();
			String date = String.valueOf(year) + ":" + String.valueOf(month);
			if (retData.containsKey(date)) {
				List<Reservation> yearsReservations = retData.get(date);
				yearsReservations.add(reservation);
				retData.put(date, yearsReservations);
			} else {
				List<Reservation> yearsReservations = new ArrayList<>();
				yearsReservations.add(reservation);
				retData.put(date, yearsReservations);
			}
		}
		return retData;
	}

	private Map<String, List<Reservation>> getDataSplitByYears(List<Reservation> reservations) {
		Map<String, List<Reservation>> retData = new HashMap<String, List<Reservation>>();
		for (Reservation reservation : reservations) {
			int year = reservation.getTimeRange().getFromDateTime().getYear();
			if (retData.containsKey(String.valueOf(year))) {
				List<Reservation> yearsReservations = retData.get(String.valueOf(year));
				yearsReservations.add(reservation);
				retData.put(String.valueOf(year), yearsReservations);
			} else {
				List<Reservation> yearsReservations = new ArrayList<>();
				yearsReservations.add(reservation);
				retData.put(String.valueOf(year), yearsReservations);
			}
		}
		return retData;
	}
		
	
	
} 

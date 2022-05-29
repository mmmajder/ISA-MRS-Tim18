package mrsa.tim018.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.ReservationRepository;
import mrsa.tim018.utils.TimeUtils;


@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
		
	@Autowired
	private AssetService assetService;

	@Autowired
	private ClientService clientService;	

	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	public Reservation findOne(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}
	
	public Reservation create(Asset asset, Client client, TimeRange timeRange) {
		Reservation reservation = new Reservation(asset, client, timeRange);
		save(reservation);
		return reservation;
	}
	
	public List<ReservationDTO> map(List<Reservation> reservations){
		List<ReservationDTO> reservationsDTO = new ArrayList<>();
		for (Reservation res : reservations) {
			ReservationDTO dto = new ReservationDTO(res);
			dto.setCancelable(isCancelable(res));
			dto.setDuration(calcDuration(res));
			boolean reviewable = isReviewable(res);
			if(reviewable && res.getStatus()!=ReservationStatus.Finished) {
				res.setStatus(ReservationStatus.Finished);
				save(res);
			}
			dto.setReviewable(reviewable);
			reservationsDTO.add(dto);
		}
		return reservationsDTO;
	}

	private boolean isCancelable(Reservation reservation) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime startDate = reservation.getTimeRange().getFromDateTime();
		LocalDateTime lastCancelationDay = startDate.minusDays(3);
		
		return lastCancelationDay.isAfter(today);
	}
	
	// DA LI VEĆ IMA REVIEW?
	private boolean isReviewable(Reservation reservation) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime endDate = reservation.getTimeRange().getToDateTime();
		boolean isCanceled = reservation.getStatus() == ReservationStatus.Canceled;
		return endDate.isBefore(today) && !isCanceled;
	}
	
	private Long calcDuration(Reservation reservation) {
		LocalDateTime from = reservation.getTimeRange().getFromDateTime();
        LocalDateTime to = reservation.getTimeRange().getToDateTime();
		Duration duration = Duration.between(from, to);
		return duration.toDays();
	}

	// VALID 
	// 1) Ako klijent otkaze ne moze opet u istom intervalu
	// 2) Ako ne postoji ovaj termin u availability
	// 3) Ako klijent ima 3 penala
	// 4) Ako datum From > To
	// 5) Ako datum from u proslosti
	public boolean isValidReservation(Reservation reservation) {
		Client client = reservation.getClient();
		Asset asset = reservation.getAsset();
		
		boolean canceled = hasBeenCanceled(reservation, client);
		boolean penalty = isPenaltyValid(client);
		boolean dateOrder = isDateOrderValid(reservation.getTimeRange());
		boolean present = isReservationInFuture(reservation.getTimeRange());
		boolean available = checkAvailabilty(reservation, asset);
		
		return canceled && penalty && dateOrder && present && available;
	}
	private boolean hasBeenCanceled(Reservation reservation, Client client){
		for (Reservation clientRes : client.getReservations()) {
			if(TimeUtils.isExactSameTimeRange(clientRes.getTimeRange(), reservation.getTimeRange())) {
				if(clientRes.getAsset().getID() == reservation.getAsset().getID() && 
				   clientRes.getStatus() == ReservationStatus.Canceled ) {
					return false;	
				}
			}
		}
		
		return true;
	}
	
	private boolean isPenaltyValid(Client client) {
		return client.getPenaltyPoints() < 3;
	}
	

	private boolean isDateOrderValid(TimeRange timeRange) {
		return timeRange.getFromDateTime().isBefore(timeRange.getToDateTime());
	}
	
	private boolean isReservationInFuture(TimeRange timeRange) {
		return LocalDateTime.now().isBefore(timeRange.getFromDateTime());
	}

	private boolean checkAvailabilty(Reservation reservation, Asset asset) {
		TimeRange timeRange = reservation.getTimeRange();
		List<TimeRange> available = asset.getCalendar().getAvailable();
		for (TimeRange timeRange2 : available) {
			if(TimeUtils.isInTimeRange(timeRange, timeRange2)) {
				return true;
			}
		}
		return false;
	}

	public Reservation makeRegularReservation(Reservation reservation) {
		boolean isValid = isValidReservation(reservation);
		if(!isValid) {
			return null;
		}
		assetService.addRegularReservation(reservation);	
		clientService.addRegularReservation(reservation);
		return save(reservation);
	}

	public Reservation cancelReservation(Reservation reservation) {
		assetService.cancelReservation(reservation);
		reservation.setStatus(ReservationStatus.Canceled);
		return save(reservation);
	}
}

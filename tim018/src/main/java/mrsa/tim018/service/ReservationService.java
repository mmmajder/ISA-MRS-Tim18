package mrsa.tim018.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.dto.ReservationRequestDTO;
import mrsa.tim018.dto.SpecialOfferReservationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.LoyaltyState;
import mrsa.tim018.model.Report;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.SpecialOffer;
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
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@Autowired
	private ReservationFinancesService reservationFinancesService;
	
	@Autowired
	private AssetCalendarSevice assetCalendarSevice;
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private RenterService renterService;

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
			ReservationDTO dto = map(res);
			reservationsDTO.add(dto);
		}
		return reservationsDTO;
	}
	
	public ReservationDTO map(Reservation res){
		ReservationDTO dto = new ReservationDTO(res);
		dto.setCancelable(isCancelable(res));
		dto.setDuration(calcDuration(res));
		boolean reviewable = isReviewable(res);
		if(reviewable && res.getStatus()!=ReservationStatus.Finished) {
			res.setStatus(ReservationStatus.Finished);
			save(res);
		}
		dto.setReviewable(reviewable);
		
		return dto;
	} 

	private boolean isCancelable(Reservation reservation) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime startDate = reservation.getTimeRange().getFromDateTime();
		LocalDateTime lastCancelationDay = startDate.minusDays(3);
		
		return lastCancelationDay.isAfter(today);
	}
	
	// DA LI VEĆ IMA REVIEW?
	private boolean isReviewable(Reservation reservation) {
		return isCompleted(reservation);
	}
	
	public boolean isCompleted(Reservation reservation) {
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
				if(clientRes.getAsset().getID().equals(reservation.getAsset().getID()) && 
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
		renterService.addRegularReservationPoints(reservation);
		return save(reservation);
	}

	public Reservation cancelReservation(Reservation reservation) {
		assetService.cancelReservation(reservation);
		int cancelationFee = reservation.getCancelationFee();
		double moneyThatRenterKeeps = reservation.getTotalPrice() * cancelationFee;
		reservation.setTotalPrice(moneyThatRenterKeeps);
		reservation.setStatus(ReservationStatus.Canceled);
		return save(reservation);
	}
	
	public List<Reservation> getCurrentRenterReservations(Long renterId){
		List<Reservation> reservations = (List<Reservation>) reservationRepository.getRenterReservations(renterId);
		reservations = reservations.stream().filter(r -> !isCompleted(r) && r.getStatus() != ReservationStatus.Canceled).collect(Collectors.toList());
		
		return reservations;
	}
	
	public List<Reservation> getPastRenterReservations(Long renterId){
		List<Reservation> reservations = (List<Reservation>) reservationRepository.getRenterReservations(renterId);
		reservations = reservations.stream().filter(r -> isCompleted(r) && r.getStatus() != ReservationStatus.Canceled).collect(Collectors.toList());
		
		return reservations;
	}
	
	public List<Reservation> getAssetReservations(Long assetId){
		return (List<Reservation>) reservationRepository.getAssetReservations(assetId);
	}
	
	public List<Report> getMonthlyReports() {
		return (List<Report>) reservationRepository.getMonthlyReports("month");
	}
	
	public List<Report> getMonthlyReports(Long assetId) {
		return (List<Report>) reservationRepository.getMonthlyReports(assetId);
	}

	
	//Transaction functions
	
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Reservation reserveSpecialOffer(SpecialOfferReservationDTO specialOfferReservationDTO) throws Exception {
		Asset asset = assetService.findOne(specialOfferReservationDTO.getAssetId());
		Reservation reservation = saveSpecialOfferReservation(specialOfferReservationDTO, asset); 
		  
		updateAssetsCalendarSpecialOffer(specialOfferReservationDTO, asset, reservation);
		
		return reservation;
	}

	private void updateAssetsCalendarSpecialOffer(SpecialOfferReservationDTO specialOfferReservationDTO, Asset asset,
			Reservation reservation) {
		asset.getCalendar().getReserved().add(reservation);     
		List<SpecialOffer> ranges = assetCalendarSevice.removeSpecialOffer(asset.getCalendar().getSpecialPrice(), specialOfferReservationDTO.getSpecialOfferId());
		if (ranges == null) {
			asset.getCalendar().setSpecialPrice(new ArrayList<SpecialOffer>());
		} else { 
			asset.getCalendar().setSpecialPrice(ranges);  
		}
		       
		assetService.save(asset);
	}

	private Reservation saveSpecialOfferReservation(SpecialOfferReservationDTO specialOfferReservationDTO, Asset asset) throws Exception {
		Client client = clientService.findOne(specialOfferReservationDTO.getClientId());
		SpecialOffer specialOffer = specialOfferService.findByIdAndLock(specialOfferReservationDTO.getSpecialOfferId());
		TimeRange timeRange = new TimeRange(false, specialOffer.getTimeRange().getFromDateTime(), specialOffer.getTimeRange().getToDateTime());
		LoyaltyState loyaltyState = loyaltyProgramService.getLoyaltyState(reservationFinancesService, loyaltyProgramService, client);
		
		if (specialOffer.getClient()!=null) {
			throw new Exception("Special offer has already been reserved");
		}
		
		Reservation reservation = new Reservation(false, asset, client, timeRange, ReservationStatus.Future, specialOffer.getDiscount(), asset.getCancelationConditions(), loyaltyState);
		reservation.setCancelationFee(asset.getCancelationConditions());
		save(reservation);
		return reservation;
	}

	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
	public Reservation makeReservation(ReservationRequestDTO reservationDto) throws Exception {
		
		Reservation reservation = saveRegularReservation(reservationDto);
		if(reservation!=null) {
			emailService.sendReservationSuccessfull(reservation);
		}
		return reservation;
	}

	private Reservation saveRegularReservation(ReservationRequestDTO reservationDto) {
		Asset asset = assetService.findOneLock(reservationDto.getAssetId());
		Client client = clientService.findOne(reservationDto.getClientId());
		TimeRange timeRange = new TimeRange(false, reservationDto.getFromDateTime(), reservationDto.getToDateTime());
		LoyaltyState loyaltyState = loyaltyProgramService.getLoyaltyState(reservationFinancesService, loyaltyProgramService, client);
		Reservation reservation = new Reservation(asset, client, timeRange, reservationDto.getTotalPrice(), loyaltyState);
		reservation.setCancelationFee(asset.getCancelationConditions());
		reservation = makeRegularReservation(reservation);
		return reservation;
	}
}

package mrsa.tim018.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.dto.ReservationRequestDTO;
import mrsa.tim018.dto.SpecialOfferReservationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.LoyaltyState;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.SpecialOffer;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.ClientService;
import mrsa.tim018.service.EmailService;
import mrsa.tim018.service.RenterService;
import mrsa.tim018.service.ReservationService;
import mrsa.tim018.service.SpecialOfferService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RenterService renterService;
	
	@Autowired
	private AssetService assetService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping(value = "/{reservationId}")
	public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long reservationId) {
		Reservation res = reservationService.findOne(reservationId);
		
		if (res == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		return new ResponseEntity<>(reservationService.map(res), HttpStatus.OK);
	}
	
	@GetMapping(value = "/current/{clientId}")
	public ResponseEntity<List<ReservationDTO>> getCurrentReservations(@PathVariable Long clientId, @RequestParam AssetType assetType) {
		Client client = clientService.findOne(clientId);
		List<Reservation> reservations = client.getReservations();
		
		reservations = reservations.stream().filter(r -> r.getTimeRange().getFromDateTime().isAfter(LocalDateTime.now())).collect(Collectors.toList());
		reservations = reservations.stream().filter(r -> r.getStatus() != ReservationStatus.Canceled).collect(Collectors.toList());
		if(assetType!=AssetType.ALL)
			reservations = reservations.stream().filter(r -> r.getAsset().getAssetType() == assetType).collect(Collectors.toList());
		
		List<ReservationDTO> reservationsDTO = reservationService.map(reservations);
		return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/history/{clientId}")
	public ResponseEntity<List<ReservationDTO>> getPastReservationsByType(@PathVariable Long clientId, @RequestParam AssetType assetType) {
		Client client = clientService.findOne(clientId);
		List<Reservation> reservations = client.getReservations();
		reservations = reservations.stream().filter(r -> r.getTimeRange().getFromDateTime().isBefore(LocalDateTime.now())).collect(Collectors.toList());
		if(assetType!=AssetType.ALL)
			reservations = reservations.stream().filter(r -> r.getAsset().getAssetType() == assetType).collect(Collectors.toList());
		
		List<ReservationDTO> reservationsDTO = reservationService.map(reservations);
		return new ResponseEntity<List<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
	}
	
	@PutMapping(value = "/cancel/{reservationId}")
	public ResponseEntity<Reservation> cancelReservation(@PathVariable Long reservationId) {
		Reservation reservation = reservationService.findOne(reservationId);
		Client client = reservation.getClient();	// TODO: penalty points?
			//TODO: tri dana do pocetka
		reservation = reservationService.cancelReservation(reservation);
		
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}
	
	@PostMapping(value = "/reserveSpecialOffer")
	public ResponseEntity<Reservation> reserveSpecialOffer(@RequestBody SpecialOfferReservationDTO specialOfferReservationDTO) {
		Asset asset = assetService.findOne(specialOfferReservationDTO.getAssetId());
		Client client = clientService.findOne(specialOfferReservationDTO.getClientId());
		SpecialOffer specialOffer = specialOfferService.findById(specialOfferReservationDTO.getSpecialOfferId());
		TimeRange timeRange = new TimeRange(false, specialOffer.getTimeRange().getFromDateTime(), specialOffer.getTimeRange().getToDateTime());
		LoyaltyState loyaltyState = new LoyaltyState(taxPercent, client.getL, renterDiscountPercent)
		Reservation reservation = new Reservation(false, asset, client, timeRange, ReservationStatus.Future, specialOffer.getDiscount(), asset.getCancelationConditions(), loyaltyState)
		//Reservation reservation = new Reservation(asset, client, timeRange);
		reservation.setCancelationFee(asset.getCancelationConditions());
		reservationService.save(reservation);
		
		asset.getCalendar().getReserved().add(reservation);
		
		assetService.save(asset);
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}
	
/*	@PostMapping(value = "/makeReservation")
	public ResponseEntity<Reservation> makeReservation(@RequestBody ReservationRequestDTO reservationDto) throws UnsupportedEncodingException, MessagingException {
		Asset asset = assetService.findOne(reservationDto.getAssetId());
		Client client = clientService.findOne(reservationDto.getClientId());
		TimeRange timeRange = new TimeRange(false, reservationDto.getFromDateTime(), reservationDto.getToDateTime());
		Reservation reservation = new Reservation(asset, client, timeRange, reservationDto.getTotalPrice());
		reservation.setCancelationFee(asset.getCancelationConditions());
		reservation = reservationService.makeRegularReservation(reservation);
		if(reservation!=null) {
			emailService.sendReservationSuccessfull(reservation);
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	*/
	@GetMapping(value = "/current/renter/{renterId}")
	public ResponseEntity<List<ReservationDTO>> getCurrentReservations(@PathVariable Long renterId) {
		Renter renter = renterService.findOne(renterId);
		
		if (renter == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<Reservation> reservations = reservationService.getCurrentRenterReservations(renterId);
		List<ReservationDTO> reservationsDTO = reservationService.map(reservations);
		
		return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/history/renter/{renterId}")
	public ResponseEntity<List<ReservationDTO>> getPastReservations(@PathVariable Long renterId) {
		Renter renter = renterService.findOne(renterId);
		
		if (renter == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<Reservation> reservations = reservationService.getPastRenterReservations(renterId);
		List<ReservationDTO> reservationsDTO = reservationService.map(reservations);
		
		return new ResponseEntity<List<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
	}
	
}

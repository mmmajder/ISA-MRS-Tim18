package mrsa.tim018.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.mapper.ReservationMapper;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.service.ClientService;
import mrsa.tim018.service.ReservationRequestService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/reservation")
public class ReservationController {

	@Autowired
	private ReservationRequestService reservationRequestService;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		List<Reservation> reservations = reservationRequestService.findAll();
		List<ReservationDTO> reservationsDTO = ReservationMapper.map(reservations);
		return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pending")
	public ResponseEntity<List<ReservationDTO>> getPendingReservations() {
		List<Reservation> reservations = reservationRequestService.findPending();
		List<ReservationDTO> reservationsDTO = ReservationMapper.map(reservations);
		return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/current/{clientId}")
	public ResponseEntity<List<ReservationDTO>> getCurrentReservations() {
		List<Reservation> reservations = reservationRequestService.findCurrent();
		List<ReservationDTO> reservationsDTO = ReservationMapper.map(reservations);
		return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/history/{clientId}")
//	public ResponseEntity<List<ReservationDTO>> getPastReservations(@PathVariable Long clientId) {
//		Client client = clientService.findOne(clientId);
//		List<Reservation> reservations = client.getReservations();
//		reservations = reservations.stream().filter(r -> r.getTimeRange().getToDateTime().isBefore(LocalDateTime.now())).collect(Collectors.toList());
//		List<ReservationDTO> reservationsDTO = ReservationMapper.map(reservations);
//		return new ResponseEntity<List<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
//	}
	
	@GetMapping(value = "/history/{clientId}")
	public ResponseEntity<List<ReservationDTO>> getPastReservationsByType(@PathVariable Long clientId, @RequestParam AssetType assetType) {
		Client client = clientService.findOne(clientId);
		List<Reservation> reservations = client.getReservations();
		reservations = reservations.stream().filter(r -> r.getTimeRange().getToDateTime().isBefore(LocalDateTime.now())).collect(Collectors.toList());
		if(assetType!=null)
			if(assetType!=AssetType.ALL)
				reservations = reservations.stream().filter(r -> r.getAsset().getAssetType() == assetType).collect(Collectors.toList());
		
		List<ReservationDTO> reservationsDTO = ReservationMapper.map(reservations);
		return new ResponseEntity<List<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
	}
	
}

package mrsa.tim018.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.ReservationRepository;


@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;	

	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	public Reservation create(Asset asset, Client client, TimeRange timeRange) {
		Reservation reservation = new Reservation(asset, client, timeRange);
		save(reservation);
		return reservation;
	}
	
	public List<ReservationDTO> map(List<Reservation> reservations){
		List<ReservationDTO> reservationsDTO = new ArrayList<>();
		for (Reservation res : reservations) {
			reservationsDTO.add(new ReservationDTO(res, isCancelable(res)));
		}
		return reservationsDTO;
	}

	private boolean isCancelable(Reservation reservation) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime startDate = reservation.getTimeRange().getFromDateTime();
		LocalDateTime lastCancelationDay = startDate.minusDays(3);
		
		return lastCancelationDay.isAfter(today);
	}
}

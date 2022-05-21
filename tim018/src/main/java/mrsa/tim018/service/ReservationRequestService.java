package mrsa.tim018.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.ReservationRepository;


@Service
public class ReservationRequestService {
	@Autowired
	private ReservationRepository reservationRepository;	

	public Reservation findOne(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}
	
	public List<Reservation> findPending() {
		return reservationRepository.findAllByStatus(ReservationStatus.Pending);
	}
	public List<Reservation> findCurrent() {
		return reservationRepository.findAllByStatus(ReservationStatus.Accepted);
	}
	
	public List<Reservation> findFinished() {
		return reservationRepository.findAllByStatus(ReservationStatus.Finished);
	}
	
	public List<Reservation> findPast() {
		return reservationRepository.findAllByEndDate(new Date(System.currentTimeMillis()));
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}
	
	public Page<Reservation> findAll(Pageable page) {
		return reservationRepository.findAll(page);
	}

	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	public void remove(Long id) {
		reservationRepository.deleteById(id);
	}
	
	public Reservation create(Asset asset, Client client, TimeRange timeRange) {
		Reservation reservation = new Reservation(asset, client, timeRange);
		save(reservation);
		return reservation;
	}
}

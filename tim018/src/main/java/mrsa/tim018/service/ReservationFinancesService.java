package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.ReservationFinances;
import mrsa.tim018.repository.ReservationFinancesRepository;

@Service
public class ReservationFinancesService {
	@Autowired
	private ReservationFinancesRepository reservationFinancesRepository;
	
	public List<ReservationFinances> findAll() {
		return reservationFinancesRepository.findAll();
	}

	public ReservationFinances save(ReservationFinances reservationFinances) {
		return reservationFinancesRepository.save(reservationFinances);
		
	}
	
	public ReservationFinances getLast() {
		return findAll().get(findAll().size()-1);
	}
}

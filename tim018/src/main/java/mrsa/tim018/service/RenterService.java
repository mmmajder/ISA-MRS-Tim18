package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationFinances;
import mrsa.tim018.model.User;
import mrsa.tim018.repository.RenterRepository;

@Service
public class RenterService {
	@Autowired
	private RenterRepository renterRepo;
	
	@Autowired
	private ReservationFinancesService reservationFinancesService;	

	public Renter findOne(Long id) {
		return renterRepo.findById(id).get();  // .orElseGet(null)
	}

	public List<Renter> findAll() {
		return renterRepo.findAll();
	}
	
	public Page<Renter> findAll(Pageable page) {
		return renterRepo.findAll(page);
	}

	public Renter save(Renter renter) {
		return renterRepo.save(renter);
	}
	
	public User save(User renter) {
		return renterRepo.save(renter);
	}

	public void remove(Integer id) {
		renterRepo.deleteById(id);
	}

	public void addRegularReservationPoints(Reservation reservation) {
		Renter renter = reservation.getAsset().getRenter();
		ReservationFinances finances = reservationFinancesService.getLast();
		renter.setLoyaltyPoints(renter.getLoyaltyPoints() + finances.getPointsPerReservation());
	}

	@Transactional
	public List<Asset> getMyAssets(Long id) {
		Renter renter = findOne(id);
		return renter.getAssets();
	}
}

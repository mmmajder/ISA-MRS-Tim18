package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Renter;
import mrsa.tim018.model.User;
import mrsa.tim018.repository.RenterRepository;

@Service
public class RenterService {
	@Autowired
	private RenterRepository renterRepo;	

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
}

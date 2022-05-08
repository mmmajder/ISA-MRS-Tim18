package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Boat;
import mrsa.tim018.repository.BoatRepository;

@Service
@Transactional
public class BoatService {
	@Autowired
	private BoatRepository boatRepository;
	
	public Boat save(Boat resort) {
		return boatRepository.save(resort);
	}
	
	public Boat findOne(Long id) {
		return boatRepository.findById(id).orElse(null);
	}

	public List<Boat> findAll() {
		return boatRepository.findAll();
	}
	
	public Boat findById(long id) {
		return boatRepository.findById(id);
	}
	
	public List<Boat> findAllByRenterId(long id) {
		return boatRepository.findAllByRenterId(id);
	}
}


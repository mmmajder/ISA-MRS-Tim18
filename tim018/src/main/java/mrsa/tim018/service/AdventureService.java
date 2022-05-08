package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Adventure;
import mrsa.tim018.repository.AdventureRepository;

@Service
@Transactional
public class AdventureService {
	@Autowired
	private AdventureRepository adventureRepository;
	
	public Adventure save(Adventure adventure) {
		return adventureRepository.save(adventure);
	}
	
	public Adventure findOne(Long id) {
		return adventureRepository.findById(id).orElse(null);
	}

	public List<Adventure> findAll() {
		return adventureRepository.findAll();
	}
	
	public Adventure findById(long id) {
		return adventureRepository.findById(id);
	}
	
	public List<Adventure> findAllByRenterId(long id) {
		return adventureRepository.findAllByRenterId(id);
	}
}

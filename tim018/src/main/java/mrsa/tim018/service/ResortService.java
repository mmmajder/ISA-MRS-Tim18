package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Resort;
import mrsa.tim018.repository.ResortRepository;

@Service
@Transactional
public class ResortService {
	@Autowired
	private ResortRepository resortRepository;
	
	public Resort save(Resort resort) {
		return resortRepository.save(resort);
	}
	
	public Resort findOne(Long id) {
		return resortRepository.findById(id).orElse(null);
	}

	public List<Resort> findAll() {
		return resortRepository.findAll();
	}
	
	public Resort findById(long id) {
		return resortRepository.findById(id);
	}
	
	public List<Resort> findAllByRenterId(long id) {
		return resortRepository.findAllByRenterId(id);
	}
}

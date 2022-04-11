package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.repository.FishingInstructorRepository;

@Service
public class FishingInstructorService {
	@Autowired
	private FishingInstructorRepository fishingInstructorRepository;	

	public FishingInstructor findOne(Integer id) {
		return fishingInstructorRepository.findById(id);  // .orElseGet(null)
	}

	public List<FishingInstructor> findAll() {
		return fishingInstructorRepository.findAll();
	}
	
	public Page<FishingInstructor> findAll(Pageable page) {
		return fishingInstructorRepository.findAll(page);
	}

	public FishingInstructor save(FishingInstructor fishingInstructor) {
		return fishingInstructorRepository.save(fishingInstructor);
	}

	public void remove(Integer id) {
		fishingInstructorRepository.deleteById(id);
	}
}

package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Registration;
import mrsa.tim018.repository.RegistrationRequestRepository;



@Service
@Transactional
public class RegistrationRequestService {

	@Autowired
	private RegistrationRequestRepository requestRepository;

	public List<Registration> findAll() {
		return requestRepository.findAll();
	}
	
	public Registration findById(long id) {
		return requestRepository.findById(id);
	}
	
	public Registration save(Registration registration) {
		return requestRepository.save(registration);
	}

}

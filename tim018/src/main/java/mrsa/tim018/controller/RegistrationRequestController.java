package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.RegistrationDTO;
import mrsa.tim018.model.Registration;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.service.RegistrationRequestService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/regRequests")
public class RegistrationRequestController {
	@Autowired
	private RegistrationRequestService registrationRequestService;
	
	@GetMapping(value = "/active")
	public ResponseEntity<List<RegistrationDTO>> getActiveRegistrationRequests() {
		List<Registration> registrations = registrationRequestService.findAll();
		List<RegistrationDTO> retData = new ArrayList<RegistrationDTO>();
		for (Registration registration : registrations) {
			if (registration.getStatus().equals(RequestStatus.Pending))
				retData.add(new RegistrationDTO(registration));
		}
		return new ResponseEntity<>(retData, HttpStatus.OK);
	}
	
	@PutMapping(value="/accept/{id}")
	public ResponseEntity<RegistrationDTO> acceptRequest(@PathVariable Integer id) {
		Registration registration = registrationRequestService.findById(id);
		if (registration==null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		registration.setStatus(RequestStatus.Accepted);
		registrationRequestService.save(registration);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value="/decline/{id}")
	public ResponseEntity<RegistrationDTO> declineRequest(@PathVariable Integer id) {
		Registration registration = registrationRequestService.findById(id);
		if (registration==null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		registration.setStatus(RequestStatus.Declined);
		registrationRequestService.save(registration);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}

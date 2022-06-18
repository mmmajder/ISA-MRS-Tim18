package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.RegisterAdminRequestDTO;
import mrsa.tim018.dto.RegistrationDTO;
import mrsa.tim018.model.Admin;
import mrsa.tim018.model.Registration;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.UserType;
import mrsa.tim018.service.AdminService;
import mrsa.tim018.service.EmailService;
import mrsa.tim018.service.RegistrationRequestService;
import mrsa.tim018.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/regRequests")
public class RegistrationRequestController {
	@Autowired
	private RegistrationRequestService registrationRequestService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.logger(RegistrationRequestController.class);
	
	@GetMapping(value = "/active")
	public ResponseEntity<List<RegistrationDTO>> getActiveRegistrationRequests() {
		List<Registration> registrations = registrationRequestService.findAll();
		List<RegistrationDTO> retData = new ArrayList<>();
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
		
		try {
			emailService.sendRegistrationResponseAsync(RequestStatus.Accepted, "");
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value="/decline/{id}")
	public ResponseEntity<RegistrationDTO> declineRequest(@PathVariable Integer id, @RequestBody String comment) {
		Registration registration = registrationRequestService.findById(id);
		if (registration==null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		registration.setStatus(RequestStatus.Declined);
		registrationRequestService.save(registration);
		try {
			emailService.sendRegistrationResponseAsync(RequestStatus.Declined, comment);
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value="/registerAdmin/")
	public ResponseEntity<RegistrationDTO> registerAdmin(@RequestBody RegisterAdminRequestDTO registerAdminRequestDTO) {
		Admin admin = new Admin();
		admin.setAlreadyLogged(false);
		admin.setAddress("");
		admin.setBiography("");
		admin.setCity("");
		admin.setPhoneNum("");
		admin.setUserType(UserType.Admin);
		admin.setState("");
		admin.setEmail(registerAdminRequestDTO.getEmail());
		admin.setDeleted(false); 
		admin.setEnabled(true); 
		admin.setFirstName(registerAdminRequestDTO.getName());
		admin.setLastName(registerAdminRequestDTO.getSurname());
		admin.setPassword(passwordEncoder.encode(registerAdminRequestDTO.getPassword()));
		adminService.save(admin);
		try {
			emailService.sendReservationSuccessfullAdmin(registerAdminRequestDTO);
			logger.info("Poslao mejl");
		}catch( Exception e ){
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	

}

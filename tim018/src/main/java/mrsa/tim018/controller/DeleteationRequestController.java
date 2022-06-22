package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.DeletationRequestDTO;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;
import mrsa.tim018.service.DeletationRequestService;
import mrsa.tim018.service.EmailService;
import mrsa.tim018.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/deletationRequest")
public class DeleteationRequestController {
	
	@Autowired
	private DeletationRequestService deletationRequestService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<DeletationRequestDTO> getDeletationRequest(@PathVariable Long id) {
		DeletationRequest request = deletationRequestService.findOne(id);
		System.out.println(request); 
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (request.isDeleted()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DeletationRequestDTO>(new DeletationRequestDTO(request), HttpStatus.OK);
	}
	 
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/all") 
	public ResponseEntity<List<DeletationRequestDTO>> getAllDeletationRequests() {
		List<DeletationRequestDTO> deletationRequestDTOs = deletationRequestService.getAllDeletationRequests();
		 
		return new ResponseEntity<>(deletationRequestDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/pending")
	public ResponseEntity<List<DeletationRequestDTO>> getPendingDeletationRequests() {

		List<DeletationRequestDTO> deletationRequestDTOs = deletationRequestService.getPendingDeletationRequests();
 
		return new ResponseEntity<>(deletationRequestDTOs, HttpStatus.OK);
	}  
	
	
	@PutMapping(value = "/deleteUser/{id}")
	public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		user.setDeleted(true);
		userService.saveChanges(user);
		try {
			emailService.sendDeleteProfile(user);
		}catch( Exception e ){
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	
	
	//conflict solve 
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/accept/{id}")  
	public ResponseEntity<DeletationRequestDTO> acceptProfileDeletationRequests(@PathVariable Long id, @RequestBody String comment) {
		try {
			DeletationRequest deletationRequest = deletationRequestService.acceptDeclineDeletionRequest(id, comment, true);
			return new ResponseEntity<>(new DeletationRequestDTO(deletationRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/decline/{id}")
	public ResponseEntity<DeletationRequestDTO> declineProfileDeletationRequests(@PathVariable Long id, @RequestBody String comment) {
		try {
			DeletationRequest deletationRequest = deletationRequestService.acceptDeclineDeletionRequest(id, comment, false);
			return new ResponseEntity<>(new DeletationRequestDTO(deletationRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

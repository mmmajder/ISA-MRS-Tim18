package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.dto.DeletationRequestDTO;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.service.DeletationRequestService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/deletationRequest")
public class DeleteationRequestController {
	
	@Autowired
	private DeletationRequestService deletationRequestService;
	
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<DeletationRequestDTO>> getAllDeletationRequests() {

		List<DeletationRequest> deletionRequests = deletationRequestService.findAll();

		// convert clients to DTOs
		List<DeletationRequestDTO> deletionRequestsDTO = new ArrayList<>();
		for (DeletationRequest s : deletionRequests) {
			deletionRequestsDTO.add(new DeletationRequestDTO(s));
		}

		return new ResponseEntity<>(deletionRequestsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pending")
	public ResponseEntity<List<DeletationRequestDTO>> getPendingDeletationRequests() {

		List<DeletationRequest> deletionRequests = deletationRequestService.findPending();

		// convert clients to DTOs
		List<DeletationRequestDTO> deletionRequestsDTO = new ArrayList<>();
		for (DeletationRequest s : deletionRequests) {
			deletionRequestsDTO.add(new DeletationRequestDTO(s));
		}

		return new ResponseEntity<>(deletionRequestsDTO, HttpStatus.OK);
	}
}

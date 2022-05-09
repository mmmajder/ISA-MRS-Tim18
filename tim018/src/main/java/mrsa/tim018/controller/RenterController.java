package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.RenterDTO;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.Renter;
import mrsa.tim018.service.DeletationRequestService;
import mrsa.tim018.service.RenterService;

@RestController
@CrossOrigin
@RequestMapping(value = "/renters")
public class RenterController {

	@Autowired
	private RenterService renterService;
	
	@Autowired
	private DeletationRequestService deleteRequestService;	

	@GetMapping(value = "/all")
	public ResponseEntity<List<RenterDTO>> getAllRenters() {

		List<Renter> fishingInstructors = renterService.findAll();

		// convert clients to DTOs
		List<RenterDTO> rentersDTO = new ArrayList<>();
		for (Renter fi : fishingInstructors) {
			rentersDTO.add(new RenterDTO(fi));
		}

		return new ResponseEntity<>(rentersDTO, HttpStatus.OK);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<RenterDTO> updateFishingInstructor(@RequestBody RenterDTO renterDTO) {

		// a student must exist
		Renter renter = renterService.findOne(renterDTO.getId());

		if (renter == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		renter.setAddress(renterDTO.getAddress());
		renter.setCity(renterDTO.getCity());
		//fishingInstructor.setDeleted(fishingInstructorDTO.isDeleted());
		renter.setFirstName(renterDTO.getFirstName());
		renter.setLastName(renterDTO.getLastName());
		//fishingInstructor.setLoyaltyPoints(fishingInstructorDTO.getLoyaltyPoints());
		renter.setPhoneNum(renterDTO.getPhoneNum());
		renter.setState(renterDTO.getState());
	//	fishingInstructor.setUserType(fishingInstructorDTO.getUserType());

		renter = renterService.save(renter);
		return new ResponseEntity<>(new RenterDTO(renter), HttpStatus.OK);
	}
	
	@PutMapping(value = "/delete", consumes = "application/json")
	public ResponseEntity<RenterDTO> deleteFishingInstructor(@RequestBody RenterDTO renterDTO) {

		// a student must exist
		Renter renter = renterService.findOne(renterDTO.getId());

		if (renter == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		renter.setDeleted(renterDTO.isDeleted());

		renter = renterService.save(renter);
		return new ResponseEntity<>(new RenterDTO(renter), HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteionRequest/{id}")
	public ResponseEntity<DeletationRequest> createDeletionRequest(@PathVariable Long id, @RequestBody String reason) {
		Renter renter = renterService.findOne(id);
		DeletationRequest deletRequest = deleteRequestService.create(renter, reason);
		System.out.println(deletRequest);
		if (deletRequest != null) {
			return new ResponseEntity<DeletationRequest>(deletRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}

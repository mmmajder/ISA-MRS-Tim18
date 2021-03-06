package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.RenterDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.service.AdminService;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.DeletationRequestService;
import mrsa.tim018.service.RenterService;

@RestController
@CrossOrigin
@RequestMapping(value = "/renters")
public class RenterController {

	@Autowired
	private RenterService renterService;
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private AdminService adminService;
	
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
		User user;
		if (renterDTO.getUserType().equals(UserType.Admin)) {
			user = adminService.findOne(renterDTO.getId());
		}else {
			user = renterService.findOne(renterDTO.getId());
		}
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		user.setAddress(renterDTO.getAddress());
		user.setCity(renterDTO.getCity());
		user.setFirstName(renterDTO.getFirstName());
		user.setLastName(renterDTO.getLastName());
		user.setPhoneNum(renterDTO.getPhoneNum());
		user.setState(renterDTO.getState());
		if (renterDTO.getUserType().equals(UserType.Admin)) {
			user = adminService.save(user);
		}else {
			user = renterService.save(user);
		}
		
		return new ResponseEntity<>(new RenterDTO(user), HttpStatus.OK);
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
		if (deletRequest != null) {
			return new ResponseEntity<>(deletRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/assetId/{assetId}")
	public ResponseEntity<Long> getRenter(@PathVariable Long assetId) {

		Asset a = assetService.findOne(assetId);
		
		if (a == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(a.getRenter().getID(), HttpStatus.OK);
	}
	
	@GetMapping(value = "owns/{renterId}/{assetId}")
	public ResponseEntity<Boolean> doesRenterOwn(@PathVariable Long renterId, @PathVariable Long assetId) {

		Renter r = renterService.findOne(renterId);
		Asset a = assetService.findOne(assetId);
		
		if (a == null || r == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(assetService.doesRenterOwnAsset(r, assetId), HttpStatus.OK);
	}

}

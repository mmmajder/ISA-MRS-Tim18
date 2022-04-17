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

import mrsa.tim018.dto.FishingInstructorDTO;
import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.service.FishingInstructorService;

@RestController
@CrossOrigin
@RequestMapping(value = "/fishingInstructors")
public class FishingInstructorController {

	@Autowired
	private FishingInstructorService fishingInstructorService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<FishingInstructorDTO>> getAllFishingInstructors() {

		List<FishingInstructor> fishingInstructors = fishingInstructorService.findAll();

		// convert clients to DTOs
		List<FishingInstructorDTO> fishingInstructorsDTO = new ArrayList<>();
		for (FishingInstructor fi : fishingInstructors) {
			fishingInstructorsDTO.add(new FishingInstructorDTO(fi));
		}

		return new ResponseEntity<>(fishingInstructorsDTO, HttpStatus.OK);
	}

	// GET /api/clients?page=0&size=5&sort=firstName,DESC
	/*@GetMapping
	public ResponseEntity<List<FishingInstructorDTO>> getFishingInstructorsPage(Pageable page) {

		// page object holds data about pagination and sorting
		// the object is created based on the url parameters "page", "size" and "sort"
		Page<FishingInstructor> fishingInstructors = fishingInstructorService.findAll(page);

		// convert students to DTOs
		List<FishingInstructorDTO> fishingInstructorsDTO = new ArrayList<>();
		for (FishingInstructor fi : fishingInstructors) {
			fishingInstructorsDTO.add(new FishingInstructorDTO(fi));
		}

		return new ResponseEntity<>(fishingInstructorsDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<FishingInstructorDTO> getFishingInstructor(@PathVariable Integer id) {

		FishingInstructor fishingInstructor = fishingInstructorService.findOne(id);

		// studen must exist
		if (fishingInstructor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<FishingInstructorDTO> saveFishingInstructor(@RequestBody FishingInstructorDTO fishingInstructorDTO) {

		FishingInstructor fishingInstructor = new FishingInstructor();
		fishingInstructor.setAddress(fishingInstructorDTO.getAddress());
		fishingInstructor.setCity(fishingInstructorDTO.getCity());
		fishingInstructor.setDeleted(fishingInstructorDTO.isDeleted());
		fishingInstructor.setFirstName(fishingInstructorDTO.getFirstName());
		fishingInstructor.setLastName(fishingInstructorDTO.getLastName());
		fishingInstructor.setLoyaltyPoints(fishingInstructorDTO.getLoyaltyPoints());
		fishingInstructor.setPhoneNum(fishingInstructorDTO.getPhoneNum());
		fishingInstructor.setState(fishingInstructorDTO.getState());
		fishingInstructor.setUserType(fishingInstructorDTO.getUserType());

		fishingInstructor = fishingInstructorService.save(fishingInstructor);
		return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<FishingInstructorDTO> updateFishingInstructor(@RequestBody FishingInstructorDTO fishingInstructorDTO) {

		// a student must exist
		FishingInstructor fishingInstructor = fishingInstructorService.findOne(Math.toIntExact(fishingInstructorDTO.getId()));

		if (fishingInstructor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		fishingInstructor.setAddress(fishingInstructorDTO.getAddress());
		fishingInstructor.setCity(fishingInstructorDTO.getCity());
		fishingInstructor.setDeleted(fishingInstructorDTO.isDeleted());
		fishingInstructor.setFirstName(fishingInstructorDTO.getFirstName());
		fishingInstructor.setLastName(fishingInstructorDTO.getLastName());
		fishingInstructor.setLoyaltyPoints(fishingInstructorDTO.getLoyaltyPoints());
		fishingInstructor.setPhoneNum(fishingInstructorDTO.getPhoneNum());
		fishingInstructor.setState(fishingInstructorDTO.getState());
		fishingInstructor.setUserType(fishingInstructorDTO.getUserType());

		fishingInstructor = fishingInstructorService.save(fishingInstructor);
		return new ResponseEntity<>(new FishingInstructorDTO(fishingInstructor), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteFishingInstructor(@PathVariable Integer id) {

		FishingInstructor fishingInstructor = fishingInstructorService.findOne(id);

		if (fishingInstructor != null) {
			fishingInstructorService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	
}

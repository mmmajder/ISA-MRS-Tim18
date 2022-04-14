package mrsa.tim018.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.FishingInstructorDTO;
import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.model.User;
import mrsa.tim018.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {

		User user = userService.findOne(id);

		// studen must exist
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}

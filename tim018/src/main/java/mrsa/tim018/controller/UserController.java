package mrsa.tim018.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.UserDTO;
import mrsa.tim018.model.User;
import mrsa.tim018.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
	
		// studen must exist
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		UserDTO dto = new UserDTO(user);
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByUsername/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		User user = userService.findByEmail(username);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	} 

	@GetMapping("/whoami")
	public ResponseEntity<UserDTO> loggedUser(Authentication authentication) {
		if(authentication == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User uuser = userService.findByEmail(authentication.getName());
		if (uuser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		UserDTO dto = new UserDTO(uuser);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/verify/{code}")
	public ResponseEntity<UserDTO> verifyUser(@PathVariable String code) {
		User user = userService.verify(code);
	    if (user == null) {
	    	return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    } 
	    UserDTO dto = new UserDTO(user);
    	return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}

package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import mrsa.tim018.dto.JwtAuthenticationRequest;
import mrsa.tim018.dto.LoginDTO;
import mrsa.tim018.dto.UserRequest;
import mrsa.tim018.dto.UserTokenState;
import mrsa.tim018.mapper.UserMapper;
import mrsa.tim018.model.Role;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.service.EmailService;
import mrsa.tim018.service.UserService;
import mrsa.tim018.utils.TokenUtils;
import net.bytebuddy.utility.RandomString;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService<User> userService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se AuthenticationException
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
																authenticationRequest.getUsername(), authenticationRequest.getPassword()));	
		} catch (AuthenticationException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		if(!user.isEnabled() || user.isDeleted()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
		}

		String jwt = tokenUtils.generateToken(user);
		int expiresIn = tokenUtils.getExpiredIn();
	
		return ResponseEntity.ok(new LoginDTO(new UserTokenState(jwt, expiresIn), user.getUserType()));
	}

	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

		User existUser = this.userService.findByEmail(userRequest.getEmail());
		if (existUser != null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
		User user = UserMapper.mapRequestToUser(userRequest);
		addRoles(user);
		
		String randomCode = RandomString.make(64);
		
		user.setVerificationCode(randomCode);
		user.setEnabled(false);
		
		user = this.userService.save(user);
		if(user.getUserType() == UserType.Client) {
			//slanje emaila
			try {
				emailService.sendNotificaitionAsync(user);
			}catch( Exception e ){
				return null;
			}	
		}
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	private void addRoles(User user) {
		List<Role> roles = new ArrayList<Role>();
		UserType userType = user.getUserType();
		roles.add((Role)userService.findRolesByUserType("ROLE_USER"));
		
		if(userType == UserType.BoatRenter) {
			roles.add((Role)userService.findRolesByUserType("ROLE_BOAT_RENTER"));
		}
		else if(userType == UserType.Client) {
			roles.add((Role)userService.findRolesByUserType("ROLE_CLIENT"));
		}
		else if(userType == UserType.FishingInstructor) {
			roles.add((Role)userService.findRolesByUserType("ROLE_FISHING_INSTRUCTOR"));
		}
		else if(userType == UserType.ResortRenter) {
			roles.add((Role)userService.findRolesByUserType("ROLE_RESORT_RENTER"));
		}
	
		user.setRoles(roles);
		
	}
}
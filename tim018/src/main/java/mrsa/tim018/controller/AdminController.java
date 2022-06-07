package mrsa.tim018.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.RegistrationDTO;
import mrsa.tim018.model.Admin;
import mrsa.tim018.model.Registration;
import mrsa.tim018.model.Report;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.service.AdminReportsService;
import mrsa.tim018.service.AdminService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private AdminReportsService reportService;
	
	@PutMapping(value="/savePassword/{id}")
	public ResponseEntity<RegistrationDTO> acceptRequest(@PathVariable Long id, @RequestBody String password) {
		Admin admin = adminService.findOne(id);
		admin.setPassword(passwordEncoder.encode(password.substring(1, password.length() - 1)));
		admin.setAlreadyLogged(true);
		adminService.save(admin); 
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/report") //LocalDateTime
	public ResponseEntity<List<Report>> getReports(@RequestParam boolean completed, @RequestParam boolean canceled,	@RequestParam boolean potential, 
			@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String period, @RequestParam String assetType) {
		List<Report> reports = reportService.getReports(period, assetType, fromDate, toDate);

		return new ResponseEntity<>(reports, HttpStatus.OK);
	}
}

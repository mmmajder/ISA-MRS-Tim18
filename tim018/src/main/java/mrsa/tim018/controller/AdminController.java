package mrsa.tim018.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import mrsa.tim018.dto.UserDTO;
import mrsa.tim018.model.Admin;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.Report;
import mrsa.tim018.model.User;
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
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value="/savePassword/{id}")
	public ResponseEntity<RegistrationDTO> acceptRequest(@PathVariable Long id, @RequestBody String password) {
		Admin admin = adminService.findOne(id);
		admin.setPassword(passwordEncoder.encode(password.substring(1, password.length() - 1)));
		admin.setAlreadyLogged(true);
		adminService.save(admin); 
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/report") //LocalDateTime
	public ResponseEntity<List<Report>> getReports(@RequestParam boolean completed, @RequestParam boolean canceled,	@RequestParam boolean potential, 
			@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String period, @RequestParam String assetType) {
		List<Report> reports = reportService.getReports(period, assetType, fromDate, toDate);

		return new ResponseEntity<>(reports, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findById/{id}") //LocalDateTime
	public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
		Admin admin = adminService.findOne(id);
		
		// studen must exist
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}
}

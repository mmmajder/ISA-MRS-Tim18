package mrsa.tim018.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.service.AssetCalendarSevice;
import mrsa.tim018.service.FishingInstructorService;

@RestController
@CrossOrigin
@RequestMapping(value = "/calendar")
public class AssetCalendarController {
	@Autowired
	private AssetCalendarSevice calendarService;
	
	@Autowired
	private FishingInstructorService instructorService;
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AppointmentCreationDTO> updateCalendar(@RequestBody AppointmentCreationDTO appointment) {
		System.out.println("Stigli smo");
		// a course must exist
		FishingInstructor instructor = instructorService.findOne(appointment.getUserId()); 
		System.out.println(instructor.getFirstName());
		if (instructor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			AssetCalendar calendar = instructor.getAssets().get(0).getCalendar();
			AssetCalendar newCalendar = calendarService.addAppointment(calendar, appointment);
			calendarService.save(newCalendar);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK);	// what to return
		
	}
}

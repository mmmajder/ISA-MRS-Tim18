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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.dto.calendar.AssetCalendarsDTO;
import mrsa.tim018.model.AppointmentType;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.SpecialOffer;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.service.AssetCalendarSevice;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.EmailService;
import mrsa.tim018.service.RenterService;
import mrsa.tim018.service.SpecialOfferService;
import mrsa.tim018.service.SubscriptionService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/calendar")
public class AssetCalendarController {
	@Autowired
	private AssetCalendarSevice calendarService;
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private RenterService renterService;
	
	@Autowired 
	private SpecialOfferService specialOfferService;
	
	@GetMapping(value = "/allCalendarsForUser/{id}") 
	public ResponseEntity<List<AssetCalendarsDTO>> getUsersCalendars(@PathVariable Long id) {
		Renter renter = renterService.findOne(id);
		List<Asset> assets = renter.getAssets();
		List<AssetCalendarsDTO> data = new ArrayList<>();
		for (Asset asset : assets) {
			try {
				data.add(new AssetCalendarsDTO(asset.getID(), asset.getName(), asset.getCalendar()));
			} catch (Exception e) { 
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/assetCalendar/{idAsset}") 
	public ResponseEntity<AssetCalendarsDTO> getAssetsCalendar(@PathVariable Long idAsset) {
		Asset asset = assetService.findById(idAsset);
		try {
			AssetCalendarsDTO data = new AssetCalendarsDTO(asset.getID(), asset.getName(), asset.getCalendar());
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<AppointmentCreationDTO> addAppointment(@RequestBody AppointmentCreationDTO appointment) {
		// a course must exist
		AppointmentCreationDTO appointmentCreationDTO = calendarService.addAppointment(appointment);
		if (appointmentCreationDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(appointmentCreationDTO, HttpStatus.OK); // what to return

	}
	
	@PostMapping(value="/remove", consumes = "application/json")
	public ResponseEntity<AppointmentCreationDTO> removeAppointment(@RequestBody AppointmentCreationDTO appointment) {
		// a course must exist
		Renter renter = renterService.findOne(appointment.getUserId());
		if (renter == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			Asset asset = assetService.findById(appointment.getAssetId());
			AssetCalendar calendar = asset.getCalendar();
			AssetCalendar newCalendar = calendarService.removeAppointment(calendar, appointment);
			calendarService.save(newCalendar);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK); // what to return

	}
	
	@GetMapping(value = "/specialOffer/{id}") 
	public ResponseEntity<SpecialOffer> getSpecOffer(@PathVariable Long id) {
		SpecialOffer specialOffer = specialOfferService.findById(id);
		if (specialOffer==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(specialOffer, HttpStatus.OK);
	}
}

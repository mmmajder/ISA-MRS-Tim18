package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.dto.calendar.AssetCalendarDTO;
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
	private AssetService assetService;
	
	@Autowired
	private RenterService renterService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired 
	private SpecialOfferService specialOfferService;
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
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
	

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/assetCalendar/{idAsset}") 
	public ResponseEntity<AssetCalendar> getAssetsCalendar(@PathVariable Long idAsset) {
		AssetCalendar data = assetService.getAssetCalendar(idAsset);
		try {
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AppointmentCreationDTO> addAppointment(@RequestBody AppointmentCreationDTO appointment) {
		// a course must exist
		Renter renter = renterService.findOne(appointment.getUserId());
		if (renter == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			Long assetId = assetService.addAppointment(appointment);
			if(appointment.getType() == AppointmentType.SpecialOffer) {
				List<Subscription> subscriptions = subscriptionService.findAssetsActiveSubscriptions(assetId);
				emailService.notifySubscribers(subscriptions, appointment);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK); // what to return

	}
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@PostMapping(value="/remove", consumes = "application/json")
	public ResponseEntity<AppointmentCreationDTO> removeAppointment(@RequestBody AppointmentCreationDTO appointment) {
		// a course must exist
		Renter renter = renterService.findOne(appointment.getUserId());
		if (renter == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			assetService.removeAppointment(appointment);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK); // what to return

	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/specialOffer/{id}") 
	public ResponseEntity<SpecialOffer> getSpecOffer(@PathVariable Long id) {
		SpecialOffer specialOffer = specialOfferService.findById(id);
		if (specialOffer==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(specialOffer, HttpStatus.OK);
	}
}

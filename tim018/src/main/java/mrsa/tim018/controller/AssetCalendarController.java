package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import mrsa.tim018.dto.calendar.AllAssetsCalendarByUserDTO;
import mrsa.tim018.dto.calendar.AssetPeriodsDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.Renter;
import mrsa.tim018.service.AssetCalendarSevice;
import mrsa.tim018.service.RenterService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/calendar")
public class AssetCalendarController<T> {
	@Autowired
	private AssetCalendarSevice calendarService;

	@Autowired
	private RenterService renterService;
	
	@GetMapping(value = "/allCalendarsForUser/{id}") 
	public ResponseEntity<List<UserCalendarsDTO>> getUsersCalendars(@PathVariable Long id) {
		Renter renter = renterService.findOne(id);
		List<Asset> assets = renter.getAssets();
		List<UserCalendarsDTO> data = new ArrayList<UserCalendarsDTO>();
		for (Asset asset : assets) {
			try {
				data.add(new UserCalendarsDTO(asset.getID(), asset.getCalendar().getAvailableSingle()));
			} catch (Exception e) {
			}
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/allAvailableSingleForUser/{id}")
	public ResponseEntity<List<AssetPeriodsDTO>> getAvailableSingleForUser(@PathVariable Long id) {
		Renter renter = renterService.findOne(id);
		List<Asset> assets = renter.getAssets();
		List<AssetPeriodsDTO> assetPeriodsDTOs = new ArrayList<AssetPeriodsDTO>();
		for (Asset asset : assets) {
			try {
				assetPeriodsDTOs.add(new AssetPeriodsDTO(asset.getID(), asset.getCalendar().getAvailableSingle()));
			} catch (Exception e) {
			}
		}
		return new ResponseEntity<>(assetPeriodsDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allAvailablePatternForUser/{id}")
	public ResponseEntity<List<AssetPeriodsDTO>> getAvailablePatternsForUser(@PathVariable Long id) {
		Renter renter = renterService.findOne(id);
		List<Asset> assets = renter.getAssets();
		List<AssetPeriodsDTO> assetPeriodsDTOs = new ArrayList<AssetPeriodsDTO>();
		for (Asset asset : assets) {
			try {
				assetPeriodsDTOs.add(new AssetPeriodsDTO(asset.getID(), asset.getCalendar().getAvailablePattern()));
			} catch (Exception e) {
			}
		}
		return new ResponseEntity<>(assetPeriodsDTOs, HttpStatus.OK);
	}
	
	

	/*@GetMapping(value = "/allAssets/{id}")
	public ResponseEntity<AllAssetsCalendarByUserDTO> getCalendars(@PathVariable Long id) {
		
		return null;

	}*/

	@PostMapping(consumes = "application/json")
	public ResponseEntity<AppointmentCreationDTO> updateCalendar(@RequestBody AppointmentCreationDTO appointment) {
		System.out.println("Stigli smo");
		// a course must exist
		Renter renter = renterService.findOne(appointment.getUserId());
		System.out.println(renter.getFirstName());
		if (renter == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			System.out.println(renter.getAssets());
			AssetCalendar calendar = renter.getAssets().get(0).getCalendar();
			AssetCalendar newCalendar = calendarService.addAppointment(calendar, appointment);
			calendarService.save(newCalendar);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK); // what to return

	}
}

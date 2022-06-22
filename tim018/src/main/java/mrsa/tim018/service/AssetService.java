package mrsa.tim018.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.dto.calendar.AssetCalendarsDTO;
import mrsa.tim018.mapper.AssetMapper;
import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.AppointmentType;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.AssetPrice;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Resort;
import mrsa.tim018.model.SpecialOffer;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.AssetRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private AssetCalendarSevice assetCalendarService;

	@Autowired
	private RenterService renterService;
	
	@Autowired
	private ResortService resortService;
	
	@Autowired
	private BoatService boatService;
	
	@Autowired
	private AdventureService adventureService;
		
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AssetPriceService assetPriceService;

	private static final String defaultAssetPicturePath = "C:\\Faks\\VI\\ISA - Internet softverske arhitekture\\ISA-MRS-Tim18\\tim18-front\\src\\assets\\images\\island_logo.png";
	

	public Asset save(Asset asset) {
		return assetRepository.save(asset);
	}
	
	public Asset findOne(Long id) {
		return assetRepository.findById(id).orElse(null);
	}
	public Asset findsAssetsWithCalendar(Long id) {
		return assetRepository.findsAssetsWithCalendar(id);
	}

	public List<Asset> findAll() {
		return assetRepository.findAll();
	}
	
	public Asset findById(long id) {
		return assetRepository.findById(id);
	}
	
	public List<Asset> findAllByRenterId(long id) {
		return assetRepository.findAllByRenterId(id);
	}

	public void addRegularReservation(Reservation reservation) {
		Asset asset = reservation.getAsset();
		TimeRange timeRange = reservation.getTimeRange();
		
		AssetCalendar calendar= asset.getCalendar();
		calendar.getReserved().add(reservation);	
		List<TimeRange> ranges = assetCalendarService.removeAvailable(calendar.getAvailable(), timeRange.getFromDateTime(), timeRange.getToDateTime());
		calendar.setAvailable(ranges);
	}

	public void cancelReservation(Reservation reservation) {
		Asset asset = reservation.getAsset();
		AssetCalendar calendar = asset.getCalendar();
		TimeRange timeRange = reservation.getTimeRange();
		
		calendar.getReserved().remove(reservation);
		
		List<TimeRange> ranges = calendar.getAvailable();
		ranges = assetCalendarService.addAvailable(ranges, timeRange.getFromDateTime(), timeRange.getToDateTime());
		calendar.setAvailable(ranges);
		
	}
	
	
	public void addSubscription(Subscription subscription) {
		Asset asset = subscription.getAsset();
		asset.getSubscriptions().add(subscription);
	}

	public void removeSubscription(Subscription subscription) {
		Asset asset = subscription.getAsset();
		asset.getSubscriptions().remove(subscription);
		save(asset);
	}
	
	public List<Asset> findByAssetTypeAndIsNotDeleted(AssetType assetType) {
		return assetRepository.findByAssetTypeAndIsDeleted(assetType, false);
	}

	public List<AssetDTO> map(List<Asset> assets) {
		List<AssetDTO> assetsDTO = new ArrayList<>();
		for (Asset asset: assets) {
			if (!asset.isDeleted()) {
				assetsDTO.add(new AssetDTO(asset));
			}
		}
		return assetsDTO;
	}

	@Transactional
	public List<AssetDTO> getAllAssets() {
		List<Asset> assets = findAll();
		return map(assets);
	}
	
	@Transactional
	public AssetCalendar getAssetCalendar(Long idAsset) {
		Asset asset = findsAssetsWithCalendar(idAsset);
		AssetCalendar calendar = asset.getCalendar();
//		List<TimeRange> available = calendar.getAvailable();
//		List<SpecialOffer> specialPrice = calendar.getSpecialPrice();
//		List<Reservation> reserved = calendar.getReserved();
		
//		AssetCalendarDTO calendarDTO = new AssetCalendarDTO(available, specialPrice, reserved);
//		AssetCalendarsDTO data = new AssetCalendarsDTO(asset.getID(), asset.getName(), calendar);
		return calendar;
	}

	@Transactional
	public List<AssetDTO> getAllAssetsByUser(Long id) {
		List<Asset> assets = renterService.getMyAssets(id);
		List<AssetDTO> assetsDTO = map(assets);
		return assetsDTO;
	}

	@Transactional
	public Long addAppointment(AppointmentCreationDTO appointment) throws MessagingException {
		Asset asset = findById(appointment.getAssetId());
		AssetCalendar calendar = asset.getCalendar();
		AssetCalendar newCalendar = assetCalendarService.addAppointment(calendar, appointment);
		assetCalendarService.save(newCalendar);
		return asset.getID();
	}

	@Transactional
	public void removeAppointment(AppointmentCreationDTO appointment) {
		Asset asset = findById(appointment.getAssetId());
		AssetCalendar calendar = asset.getCalendar();
		AssetCalendar newCalendar = assetCalendarService.removeAppointment(calendar, appointment);
		assetCalendarService.save(newCalendar);
	}

	@Transactional
	public AssetDTO createAsset(AssetDTO assetDto) {
		AssetType type = assetDto.getAssetType();
		Long renterId = assetDto.getRenterId();
		
		switch (type) {
		case RESORT: 
			Resort resort = AssetMapper.mapToResort(assetDto);
			setCalendarAndRenter(resort, renterId);
			resort = resortService.save(resort);
			createPrice(resort.getID(), assetDto.getPrice());
			setDefaultPicture(resort.getID());
			return new AssetDTO(resort);
		case BOAT:  
			Boat boat = AssetMapper.mapToBoat(assetDto);
			setCalendarAndRenter(boat, renterId);
			boat = boatService.save(boat);
			createPrice(boat.getID(), assetDto.getPrice());
			setDefaultPicture(boat.getID());
			return new AssetDTO(boat);
		case FISHING_ADVENTURE:  
			Adventure adventure = AssetMapper.mapToAdventure(assetDto);
			setCalendarAndRenter(adventure, renterId);
			adventure = adventureService.save(adventure);
			createPrice(adventure.getID(), assetDto.getPrice());
			setDefaultPicture(adventure.getID());
			return new AssetDTO(adventure);
		default: 
			return null;
		}
	}
	
	private void setDefaultPicture(Long assetId) {
		try {
			byte[] imageFyle = imageService.readImageFromAddress(defaultAssetPicturePath);
			imageService.store(assetId, imageFyle);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createPrice(Long assetId, double price) {
		LocalDate startDate = LocalDate.now();
		AssetPrice assetPrice = new AssetPrice(price, startDate, assetId);
		assetPriceService.save(assetPrice);
	}
	
	private void setCalendarAndRenter(Asset asset, Long renterId) {
		AssetCalendar assetCalendar = assetCalendarService.createNewCalendar();
		asset.setCalendar(assetCalendar);
		Renter renter = renterService.findOne(renterId);
		asset.setRenter(renter);
	}
}

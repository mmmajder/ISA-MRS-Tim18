package mrsa.tim018.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.mapper.AssetMapper;
import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.AssetPrice;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Resort;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.model.User;
import mrsa.tim018.repository.AdventureRepository;
import mrsa.tim018.repository.AssetRepository;
import mrsa.tim018.repository.BoatRepository;
import mrsa.tim018.repository.ResortRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private AssetCalendarSevice assetCalendarService;
	
	@Autowired
	private ResortService resortService;
	
	@Autowired
	private BoatService boatService;
	
	@Autowired
	private AdventureService adventureService;
	
	@Autowired 
	private RenterService renterService;
	
	@Autowired
	private AssetPriceService assetPriceService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private AdventureRepository adventureRepository;
	
	@Autowired
	private ResortRepository resortRepository;
	
	@Autowired
	private UserService userService;
	
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
		
	private void createPrice(Long assetId, double price) {
		LocalDate startDate = LocalDate.now();
		AssetPrice assetPrice = new AssetPrice(price, startDate, assetId);
		assetPriceService.save(assetPrice);
	}

	@Transactional(readOnly=false)
	public AssetDTO updateAsset(Long id, AssetDTO assetDto) {
		AssetType type = assetDto.getAssetType();
		
		switch (type) {
		case RESORT: 
			return updateResort(id, assetDto);
		case BOAT: 
			return updateBoat(id, assetDto);
		case FISHING_ADVENTURE: 
			return updateAdventure(id, assetDto);
		default:  
			return null;
		}
	}
	private AssetDTO updateResort(Long id,AssetDTO assetDto) {
		Resort updatedData = AssetMapper.mapToResort(assetDto);
		Resort resortToUpdate = resortService.findOne(id);
		
		if (resortToUpdate != null)
		{
			// changes only user-changable attributes
			updateAsset(resortToUpdate, updatedData);
			resortToUpdate.setNumberOfRooms(updatedData.getNumberOfRooms());
			resortToUpdate.setNumberOfBeds(updatedData.getNumberOfBeds());
			resortService.save(resortToUpdate);
			return new AssetDTO(resortToUpdate);
		} else
			return null;
	}
	
	private AssetDTO updateBoat(Long id, AssetDTO assetDto) {
		Boat updatedData = AssetMapper.mapToBoat(assetDto);
		Boat boatToUpdate = boatService.findOne(id);
		
		if (boatToUpdate != null)
		{
			// changes only user-changable attributes
			updateAsset(boatToUpdate, updatedData);
			updateBoatSpecificAttributes(boatToUpdate, updatedData);
			boatService.save(boatToUpdate);
			return new AssetDTO(boatToUpdate);
		} else
			return null;
	}
	
	private AssetDTO updateAdventure(Long id, AssetDTO assetDto) {
		Adventure updatedData = AssetMapper.mapToAdventure(assetDto);
		Adventure adventureToUpdate = adventureService.findOne(id);
		
		if (adventureToUpdate != null)
		{
			updateAsset(adventureToUpdate, updatedData);
			adventureToUpdate.setFishingEquipment(updatedData.getFishingEquipment());
			adventureService.save(adventureToUpdate);
			return new AssetDTO(adventureToUpdate);
		} else
			return null;
	}
	
	private void updateBoatSpecificAttributes(Boat boatToUpdate, Boat updatedData) {
		boatToUpdate.setBoatType(updatedData.getBoatType());
		boatToUpdate.setLength(updatedData.getLength());
		boatToUpdate.setNumOfMotor(updatedData.getNumOfMotor());
		boatToUpdate.setMotorPower(updatedData.getMotorPower());
		boatToUpdate.setMaxSpeed(updatedData.getMaxSpeed());
		boatToUpdate.setNavigationEquipment(updatedData.getNavigationEquipment());
		boatToUpdate.setFishingEquipment(updatedData.getFishingEquipment());
	}
	
	private void updateAsset(Asset assetToUpdate, Asset updatedData) {
		assetToUpdate.setAddress(updatedData.getAddress());
		assetToUpdate.setCancelationConditions(updatedData.getCancelationConditions());
		assetToUpdate.setDescription(updatedData.getDescription());
		assetToUpdate.setName(updatedData.getName());
		assetToUpdate.setNumOfPeople(updatedData.getNumOfPeople());
		assetToUpdate.setRules(updatedData.getRules());
	}

	@Transactional(readOnly = false)
	public Asset findOneLock(Long assetId) {
		Asset asset = assetRepository.findById(assetId).orElse(null);
		if (asset==null) {
			return asset;
		}
		switch (asset.getAssetType()) {
		case BOAT:
			asset = boatRepository.findOneByIdLock(assetId);
			break;
		case FISHING_ADVENTURE:
			asset = adventureRepository.findOneByIdLock(assetId);
			break;
		case RESORT:
			asset = resortRepository.findOneByIdLock(assetId);
			break;
		default:
			break;
		}
		return asset;
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
	
	public boolean verifyAssetDto(AssetDTO assetDto) {
		if (assetDto == null) return false;
		if (assetDto.getName() == null || assetDto.getName().isEmpty()) return false;
		if (assetDto.getAddress() == null || assetDto.getAddress().isEmpty()) return false;
		if (assetDto.getDescription() == null || assetDto.getDescription().isEmpty()) return false;
		if (assetDto.getRules() == null || assetDto.getRules().isEmpty()) return false;
		if (assetDto.getNumOfPeople() <= 0) return false;
		if (assetDto.getCancelationConditions() < 0 || assetDto.getCancelationConditions() > 100) return false;
		
		switch(assetDto.getAssetType()) {
			case RESORT: 
				if (assetDto.getNumberOfBeds() <= 0) return false;
				if (assetDto.getNumberOfRooms() <= 0) return false;
				break;
			case BOAT:
				if (assetDto.getBoatType() == null || assetDto.getBoatType().isEmpty()) return false;
				if (assetDto.getNavigationEquipment() == null || assetDto.getNavigationEquipment().isEmpty()) return false;
				if (assetDto.getLength() <= 0) return false;
				if (assetDto.getMaxSpeed() <= 0) return false;
				if (assetDto.getMotorPower() <= 0) return false;
				if (assetDto.getNumOfMotor() <= 0) return false;
				if (assetDto.getFishingEquipment() == null || assetDto.getFishingEquipment().isEmpty()) return false;
				break;
			case FISHING_ADVENTURE:
				if (assetDto.getFishingEquipment() == null || assetDto.getFishingEquipment().isEmpty()) return false;
				break;
			default:
				return false;
		}
		
		return true;
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
	
	
	private void setCalendarAndRenter(Asset asset, Long renterId) {
		AssetCalendar assetCalendar = assetCalendarService.createNewCalendar();
		asset.setCalendar(assetCalendar);
		Renter renter = renterService.findOne(renterId);
		asset.setRenter(renter);
	}
	
	public boolean doesRenterOwnAsset(Renter renter, long assetId) {
		for (Asset asset : renter.getAssets())
			if (asset.getID().equals(assetId))
				return true;
		
		return false;
	}
	
	public boolean doesRenterOwnAsset(Authentication authentication, long assetId) {
		User uuser = userService.findByEmail(authentication.getName());
		Renter renter = renterService.findOne(uuser.getID());
		
		for (Asset asset : renter.getAssets())
			if (asset.getID().equals(assetId))
				return true;
		
		return false;
	}
}

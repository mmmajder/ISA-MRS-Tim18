package mrsa.tim018.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import mrsa.tim018.repository.AssetRepository;

@Service
@Transactional
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
	private AssetCalendarSevice assetCalendarSevice;
	
	@Autowired 
	private RenterService renterService;
	
	@Autowired
	private AssetPriceService assetPriceService;
	
	@Autowired
	private ImageService imageService;
	
	private static final String defaultAssetPicturePath = "C:\\Faks\\VI\\ISA - Internet softverske arhitekture\\ISA-MRS-Tim18\\tim18-front\\src\\assets\\images\\island_logo.png";

	public Asset save(Asset asset) {
		return assetRepository.save(asset);
	}
	
	public Asset findOne(Long id) {
		return assetRepository.findById(id).orElse(null);
	}

	public List<Asset> findAll() {
		return assetRepository.findAll();
	}
	
	public Asset findById(long id) {
		return assetRepository.findOneById(id);
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
	
	private void setCalendarAndRenter(Asset asset, Long renterId) {
		AssetCalendar assetCalendar = assetCalendarSevice.createNewCalendar();
		asset.setCalendar(assetCalendar);
		Renter renter = renterService.findOne(renterId);
		asset.setRenter(renter);
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

	public Asset findOneLock(Long assetId) {
		return assetRepository.findOneById(assetId);
	}
}

package mrsa.tim018.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.mapper.AssetMapper;
import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.AssetPrice;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Report;
import mrsa.tim018.model.Resort;
import mrsa.tim018.service.AdventureService;
import mrsa.tim018.service.AssetCalendarSevice;
import mrsa.tim018.service.AssetPriceService;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.BoatService;
import mrsa.tim018.service.EmailService;
import mrsa.tim018.service.ImageService;
import mrsa.tim018.service.RenterService;
import mrsa.tim018.service.ReportService;
import mrsa.tim018.service.ResortService;
import mrsa.tim018.utils.TimeUtils;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/assets")
public class AssetController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AssetService assetService;
	
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
	private ReportService reportService;
	

	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO assetDto) {
		AssetDTO created = assetService.createAsset(assetDto);
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@PutMapping(value="/{id}", consumes = "application/json" )
	public ResponseEntity<AssetDTO> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDto) {
		AssetType type = assetDto.getAssetType();
		
		switch (type) {
		case RESORT: 
			return updateResort(id, assetDto);
		case BOAT: 
			return updateBoat(id, assetDto);
		case FISHING_ADVENTURE: 
			return updateAdventure(id, assetDto);
		default:  
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@DeleteMapping(value="/{id}" )
	public ResponseEntity<AssetDTO> deleteAsset(@PathVariable long id) {
		Asset asset = assetService.findById(id);
		if (asset==null) {
			return null;
		}
		asset.setDeleted(true);
		assetService.save(asset);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value="/deleteAssetAdmin/{id}" )
	public ResponseEntity<AssetDTO> deleteAssetAdmin(@PathVariable long id) {
		Asset asset = assetService.findById(id);
		if (asset==null) {
			return null;
		}
		asset.setDeleted(true);
		assetService.save(asset);
		try {
			emailService.sendDeleteAsset(asset);
		}catch( Exception e ){
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	private ResponseEntity<AssetDTO> updateResort(Long id,AssetDTO assetDto) {
		Resort updatedData = AssetMapper.mapToResort(assetDto);
		Resort resortToUpdate = resortService.findOne(id);
		
		if (resortToUpdate != null)
		{
			// changes only user-changable attributes
			updateAsset(resortToUpdate, updatedData);
			resortToUpdate.setNumberOfRooms(updatedData.getNumberOfRooms());
			resortToUpdate.setNumberOfBeds(updatedData.getNumberOfBeds());
			resortService.save(resortToUpdate);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	private ResponseEntity<AssetDTO> updateBoat(Long id, AssetDTO assetDto) {
		Boat updatedData = AssetMapper.mapToBoat(assetDto);
		Boat boatToUpdate = boatService.findOne(id);
		
		if (boatToUpdate != null)
		{
			// changes only user-changable attributes
			updateAsset(boatToUpdate, updatedData);
			updateBoatSpecificAttributes(boatToUpdate, updatedData);
			boatService.save(boatToUpdate);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	private ResponseEntity<AssetDTO> updateAdventure(Long id, AssetDTO assetDto) {
		Adventure updatedData = AssetMapper.mapToAdventure(assetDto);
		Adventure adventureToUpdate = adventureService.findOne(id);
		
		if (adventureToUpdate != null)
		{
			updateAsset(adventureToUpdate, updatedData);
			adventureToUpdate.setFishingEquipment(updatedData.getFishingEquipment());
			adventureService.save(adventureToUpdate);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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
	
	@GetMapping
	public ResponseEntity<List<AssetDTO>> getAssets() {
		List<AssetDTO> assetsDTO = assetService.getAllAssets();
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<AssetDTO>> searchAssets(@RequestParam AssetType assetType, @RequestParam String address,
			@RequestParam int numOfPeople, @RequestParam double price, @RequestParam double mark, @RequestParam String startDate, @RequestParam String endDate) {
		List<Asset> assets = assetService.findAll();
		assets = filterAssets(assets, assetType, address, numOfPeople,  price,  mark, TimeUtils.getLocalDate(startDate), TimeUtils.getLocalDate(endDate));
		List<AssetDTO> assetDTOs = mapAssetsToDto(assets);
		
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@GetMapping(value = "/search/{renterId}")
	public ResponseEntity<List<AssetDTO>> searchRenterAssets(@PathVariable Long renterId, @RequestParam String address,
			@RequestParam int numOfPeople, @RequestParam double price, @RequestParam double mark, @RequestParam String startDate, @RequestParam String endDate) {
		List<Asset> assets = assetService.findAllByRenterId(renterId);
		assets = filterAssets(assets, AssetType.ALL, address, numOfPeople,  price,  mark, TimeUtils.getLocalDate(startDate), TimeUtils.getLocalDate(endDate));
		List<AssetDTO> assetDTOs = mapAssetsToDto(assets);
		
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
	
	private List<Asset> filterAssets(List<Asset> assets, AssetType assetType, String address, int numOfPeople, double price, double mark, LocalDate startDate, LocalDate endDate) {
		if (price != 0)
			assets = assets.stream().filter(a -> a.getPrice() <= price).collect(Collectors.toList());
		if (mark != 0)
			assets = assets.stream().filter(a -> a.getAverageRating() >= mark).collect(Collectors.toList());
		if (numOfPeople != 0)
			assets = assets.stream().filter(a -> a.getNumOfPeople() >= numOfPeople).collect(Collectors.toList());
		if (!address.isEmpty())
			assets = assets.stream().filter(a -> a.getAddress().toLowerCase().contains(address.toLowerCase())).collect(Collectors.toList());
		if(assetType!=AssetType.ALL)
			assets = assets.stream().filter(a -> a.getAssetType() == assetType).collect(Collectors.toList());
		assets = assetCalendarSevice.availableInRange(assets, startDate, endDate);
		return assets;
	} 
	
	private List<AssetDTO> mapAssetsToDto(List<Asset> assets){
		List<AssetDTO> assetsDTO = new ArrayList<>();
		
		for (Asset a : assets) {
			assetsDTO.add(new AssetDTO(a));
		}
		
		return assetsDTO;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AssetDTO> getAsset(@PathVariable Long id) {
		Asset asset = assetService.findOne(id);
		AssetType type = asset.getAssetType();
		AssetDTO assetDTO;
		switch (type) {
		case RESORT: 
			Resort resort = resortService.findOne(id);
			assetDTO = new AssetDTO(resort); 
			break;
		case BOAT: 
			Boat boat = boatService.findOne(id);
			assetDTO = new AssetDTO(boat); 
			break;
		case FISHING_ADVENTURE: 
			Adventure adventure = adventureService.findOne(id);
			assetDTO = new AssetDTO(adventure); 
			break;
		default: 
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(assetDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@GetMapping(value = "/all/{id}")
	public ResponseEntity<List<AssetDTO>> getAllAssetsByUser(@PathVariable Long id) {
		List<AssetDTO> assetsDTO = assetService.getAllAssetsByUser(id);
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@GetMapping(value = "/renter/{id}")
	public ResponseEntity<List<AssetDTO>> getAssetsByRenter(@PathVariable Long id) {
		List<Asset> assets = renterService.getMyAssets(id);
		List<AssetDTO> assetsDTO = assetService.map(assets);
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER')")
	@GetMapping(value = "/report/{renterId}") //LocalDateTime
	public ResponseEntity<List<Report>> getReports(@PathVariable Long renterId, @RequestParam boolean completed, @RequestParam boolean canceled,
			@RequestParam boolean potential, @RequestParam String fromDate, @RequestParam String toDate, @RequestParam String period, @RequestParam Long assetId) {
		List<Report> reports = reportService.getReports(renterId, completed, canceled, potential, period, assetId, fromDate, toDate);
		return new ResponseEntity<>(reports, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findByType/{assetType}")
	public ResponseEntity<List<AssetDTO>> getAssetsByType(@PathVariable AssetType assetType) {
		if(assetType == AssetType.ALL) {
			return getAssets();
		}
		List<Asset> assets =  assetService.findByAssetTypeAndIsNotDeleted(assetType);	
		List<AssetDTO> assetsDTO = assetService.map(assets);
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
}


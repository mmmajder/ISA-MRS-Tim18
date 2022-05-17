package mrsa.tim018.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Resort;
import mrsa.tim018.service.AdventureService;
import mrsa.tim018.service.AssetCalendarSevice;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.BoatService;
import mrsa.tim018.service.RenterService;
import mrsa.tim018.service.ResortService;
import mrsa.tim018.utils.TimeUtils;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/assets")
public class AssetController {
	
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
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AssetDTO> saveAsset(@RequestBody AssetDTO assetDto) {
		AssetType type = assetDto.getAssetType();
		AssetCalendar assetCalendar;
		Renter renter = renterService.findOne(assetDto.getRenterId());
		switch (type) {
		case RESORT: 
			Resort resort = AssetMapper.mapToResort(assetDto);
			assetCalendar = assetCalendarSevice.createNewCalendar();
			resort.setCalendar(assetCalendar);
			resort.setRenter(renter);
			resort = resortService.save(resort);
			return new ResponseEntity<>(new AssetDTO(resort), HttpStatus.CREATED);
		case BOAT:  
			Boat boat = AssetMapper.mapToBoat(assetDto);
			assetCalendar = assetCalendarSevice.createNewCalendar();
			boat.setCalendar(assetCalendar);
			boat.setRenter(renter);
			boat = boatService.save(boat);
			return new ResponseEntity<>(new AssetDTO(boat), HttpStatus.CREATED);
		case FISHING_ADVENTURE:  
			Adventure adventure = AssetMapper.mapToAdventure(assetDto);
			assetCalendar = assetCalendarSevice.createNewCalendar();
			adventure.setCalendar(assetCalendar);
			adventure.setRenter(renter);
			adventure = adventureService.save(adventure);
			return new ResponseEntity<>(new AssetDTO(adventure), HttpStatus.CREATED);
		default: 
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
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
		
//		Asset updatedAsset = AssetMapper.mapToAsset(assetDto);
//		Asset assetToUpdate = assetService.findOne(id);
//		
//		if (assetToUpdate != null)
//		{
//			// changes only user-changable attributes
//			assetToUpdate.setAddress(updatedAsset.getAddress());
//			assetToUpdate.setCancelationConditions(updatedAsset.getCancelationConditions());
//			assetToUpdate.setDescription(updatedAsset.getDescription());
//			assetToUpdate.setName(updatedAsset.getName());
//			assetToUpdate.setNumOfPeople(updatedAsset.getNumOfPeople());
//			assetToUpdate.setRules(updatedAsset.getRules());
//			assetService.save(assetToUpdate);
//			return new ResponseEntity<>(HttpStatus.ACCEPTED);
//		} else
//			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
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
	
	
	public ResponseEntity<AssetDTO> updateResort(Long id,AssetDTO assetDto) {
		Resort updatedResort = AssetMapper.mapToResort(assetDto);
		Resort resortToUpdate = resortService.findOne(id);
		
		if (resortToUpdate != null)
		{
			// changes only user-changable attributes
			resortToUpdate.setAddress(updatedResort.getAddress());
			resortToUpdate.setCancelationConditions(updatedResort.getCancelationConditions());
			resortToUpdate.setDescription(updatedResort.getDescription());
			resortToUpdate.setName(updatedResort.getName());
			resortToUpdate.setNumOfPeople(updatedResort.getNumOfPeople());
			resortToUpdate.setRules(updatedResort.getRules());
			resortToUpdate.setNumberOfRooms(updatedResort.getNumberOfRooms());
			resortToUpdate.setNumberOfBeds(updatedResort.getNumberOfBeds());
			resortService.save(resortToUpdate);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseEntity<AssetDTO> updateBoat(Long id, AssetDTO assetDto) {
		Boat updatedBoat = AssetMapper.mapToBoat(assetDto);
		Boat boatToUpdate = boatService.findOne(id);
		
		if (boatToUpdate != null)
		{
			// changes only user-changable attributes
			boatToUpdate.setAddress(updatedBoat.getAddress());
			boatToUpdate.setCancelationConditions(updatedBoat.getCancelationConditions());
			boatToUpdate.setDescription(updatedBoat.getDescription());
			boatToUpdate.setName(updatedBoat.getName());
			boatToUpdate.setNumOfPeople(updatedBoat.getNumOfPeople());
			boatToUpdate.setRules(updatedBoat.getRules());
			boatToUpdate.setBoatType(updatedBoat.getBoatType());
			boatToUpdate.setLength(updatedBoat.getLength());
			boatToUpdate.setNumOfMotor(updatedBoat.getNumOfMotor());
			boatToUpdate.setMotorPower(updatedBoat.getMotorPower());
			boatToUpdate.setMaxSpeed(updatedBoat.getMaxSpeed());
			boatToUpdate.setNavigationEquipment(updatedBoat.getNavigationEquipment());
			boatToUpdate.setFishingEquipment(updatedBoat.getFishingEquipment());
			boatService.save(updatedBoat);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseEntity<AssetDTO> updateAdventure(Long id, AssetDTO assetDto) {
		Adventure updatedAdventure = AssetMapper.mapToAdventure(assetDto);
		Adventure adventureToUpdate = adventureService.findOne(id);
		
		if (adventureToUpdate != null)
		{
			// changes only user-changable attributes
			adventureToUpdate.setAddress(updatedAdventure.getAddress());
			adventureToUpdate.setCancelationConditions(updatedAdventure.getCancelationConditions());
			adventureToUpdate.setDescription(updatedAdventure.getDescription());
			adventureToUpdate.setName(updatedAdventure.getName());
			adventureToUpdate.setNumOfPeople(updatedAdventure.getNumOfPeople());
			adventureToUpdate.setRules(updatedAdventure.getRules());
			adventureToUpdate.setFishingEquipment(updatedAdventure.getFishingEquipment());
			adventureService.save(updatedAdventure);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	
	@GetMapping
	public ResponseEntity<List<AssetDTO>> getAssets() {
		List<Asset> assets = assetService.findAll();
		List<AssetDTO> assetsDTO = new ArrayList<>();
		
		for (Asset a : assets) {
			assetsDTO.add(new AssetDTO(a));
		}
		
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<AssetDTO>> searchAssets(@RequestParam String address,
			@RequestParam int numOfPeople, @RequestParam double price, @RequestParam double mark, @RequestParam String startDate, @RequestParam String endDate) {
		List<Asset> assets = assetService.findAll();
		assets = filterAssets(assets, address, numOfPeople,  price,  mark, TimeUtils.getLocalDate(startDate), TimeUtils.getLocalDate(endDate));
		List<AssetDTO> assetDTOs = mapAssetsToDto(assets);
		
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{renterId}")
	public ResponseEntity<List<AssetDTO>> searchRenterAssets(@PathVariable Long renterId, @RequestParam String address,
			@RequestParam int numOfPeople, @RequestParam double price, @RequestParam double mark, @RequestParam String startDate, @RequestParam String endDate) {
		List<Asset> assets = assetService.findAllByRenterId(renterId);
		assets = filterAssets(assets, address, numOfPeople,  price,  mark, TimeUtils.getLocalDate(startDate), TimeUtils.getLocalDate(endDate));
		List<AssetDTO> assetDTOs = mapAssetsToDto(assets);
		
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
	
	private List<Asset> filterAssets(List<Asset> assets, String address, int numOfPeople, double price, double mark, LocalDate startDate, LocalDate endDate) {
		if (price != 0)
			assets = assets.stream().filter(a -> a.getPrice() <= price).collect(Collectors.toList());
		if (mark != 0)
			assets = assets.stream().filter(a -> a.getAverageRating() >= mark).collect(Collectors.toList());
		if (numOfPeople != 0)
			assets = assets.stream().filter(a -> a.getNumOfPeople() >= numOfPeople).collect(Collectors.toList());
		if (!address.isEmpty())
			assets = assets.stream().filter(a -> a.getAddress().toLowerCase().contains(address.toLowerCase())).collect(Collectors.toList());
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
	
	@GetMapping(value = "/all/{id}")
	public ResponseEntity<List<AssetDTO>> getAllAssetsByUser(@PathVariable Long id) {
		Renter renter = renterService.findOne(id);
		List<AssetDTO> assetsDTO = new ArrayList<>();
		for (Asset asset: renter.getAssets()) {
			if (!asset.isDeleted()) {
				assetsDTO.add(new AssetDTO(asset));
			}
		}
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/renter/{id}")
	public ResponseEntity<List<AssetDTO>> getAssetsByRenter(@PathVariable Long id) {
		
		Renter renter = renterService.findOne(id);
		List<AssetDTO> assetsDTO = new ArrayList<>();
		for (Asset asset: renter.getAssets()) {
			if (!asset.isDeleted()) {
				assetsDTO.add(new AssetDTO(asset));
			}
		}
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
}


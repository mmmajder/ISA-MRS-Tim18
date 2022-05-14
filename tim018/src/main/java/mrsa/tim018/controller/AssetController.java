package mrsa.tim018.controller;

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
	public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO assetDto) {
		AssetType type = assetDto.getAssetType();
		
		switch (type) {
		case RESORT: 
			Resort resort = AssetMapper.mapToResort(assetDto);
			setCalendarAndRenter(resort);
			resort = resortService.save(resort);
			return new ResponseEntity<>(new AssetDTO(resort), HttpStatus.CREATED);
		case BOAT:  
			Boat boat = AssetMapper.mapToBoat(assetDto);
			setCalendarAndRenter(boat);
			boat = boatService.save(boat);
			return new ResponseEntity<>(new AssetDTO(boat), HttpStatus.CREATED);
		case FISHING_ADVENTURE:  
			Adventure adventure = AssetMapper.mapToAdventure(assetDto);
			setCalendarAndRenter(adventure);
			adventure = adventureService.save(adventure);
			return new ResponseEntity<>(new AssetDTO(adventure), HttpStatus.CREATED);
		default: 
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	private void setCalendarAndRenter(Asset asset) {
		AssetCalendar assetCalendar = assetCalendarSevice.createNewCalendar();
		asset.setCalendar(assetCalendar);
		Renter renter = renterService.findOne(asset.getRenter().getID());
		asset.setRenter(renter);
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
		List<Asset> assets = assetService.findAll();
		List<AssetDTO> assetsDTO = new ArrayList<>();
		
		for (Asset a : assets) {
			assetsDTO.add(new AssetDTO(a));
		}
		
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<AssetDTO>> searchAssets(@RequestParam String address,
			@RequestParam int numOfPeople, @RequestParam double price, @RequestParam double mark) {
		List<Asset> assets = assetService.findAll();
		assets = filterAssets(assets, address, numOfPeople,  price,  mark);
		List<AssetDTO> assetDTOs = mapAssetsToDto(assets);
		
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{renterId}")
	public ResponseEntity<List<AssetDTO>> searchRenterAssets(@PathVariable Long renterId, @RequestParam String address,
			@RequestParam int numOfPeople, @RequestParam double price, @RequestParam double mark) {
		List<Asset> assets = assetService.findAllByRenterId(renterId);
		assets = filterAssets(assets, address, numOfPeople,  price,  mark);
		List<AssetDTO> assetDTOs = mapAssetsToDto(assets);
		
		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
	
	private List<Asset> filterAssets(List<Asset> assets, String address, int numOfPeople, double price, double mark) {
		if (price != 0)
			assets = assets.stream().filter(a -> a.getPrice() <= price).collect(Collectors.toList());
		if (mark != 0)
			assets = assets.stream().filter(a -> a.getAverageRating() >= mark).collect(Collectors.toList());
		if (numOfPeople != 0)
			assets = assets.stream().filter(a -> a.getNumOfPeople() >= numOfPeople).collect(Collectors.toList());
		if (!address.isEmpty())
			assets = assets.stream().filter(a -> a.getAddress().toLowerCase().contains(address.toLowerCase())).collect(Collectors.toList());
		
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


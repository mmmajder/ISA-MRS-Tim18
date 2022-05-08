package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.mapper.AssetMapper;
import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Resort;
import mrsa.tim018.service.AdventureService;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.BoatService;
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
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AssetDTO> saveAsset(@RequestBody AssetDTO assetDto) {
		AssetType type = assetDto.getAssetType();
		
		switch (type) {
		case RESORT: 
			Resort resort = AssetMapper.mapToResort(assetDto);
			resort = resortService.save(resort);
			return new ResponseEntity<>(new AssetDTO(resort), HttpStatus.CREATED);
		case BOAT: 
			Boat boat = AssetMapper.mapToBoat(assetDto);
			boat = boatService.save(boat);
			return new ResponseEntity<>(new AssetDTO(boat), HttpStatus.CREATED);
		case FISHING_ADVENTURE: 
			Adventure adventure = AssetMapper.mapToAdventure(assetDto);
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
	
	
	@GetMapping(value = "/renter/{id}")
	public ResponseEntity<List<AssetDTO>> getAssetsByRenter(@PathVariable Long renterId) {
			
		List<Asset> assets = assetService.findAll();
		List<AssetDTO> assetsDTO = new ArrayList<>();
		
		for (Asset a : assets) {
			assetsDTO.add(new AssetDTO(a));
		}
		
		assetsDTO = assetsDTO.stream()
				.filter(a -> a.getRenter().getID().equals(renterId))
				.collect(Collectors.toList());

		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
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

		List<Asset> assets = assetService.findAllByRenterId(id);
		List<AssetDTO> assetDTOs = new ArrayList<AssetDTO>();
		for (Asset asset : assets) {
			assetDTOs.add(new AssetDTO(asset));
		}

		return new ResponseEntity<>(assetDTOs, HttpStatus.OK);
	}
}


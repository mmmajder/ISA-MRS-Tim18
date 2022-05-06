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
import mrsa.tim018.model.Asset;
import mrsa.tim018.service.AssetService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/assets")
public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AssetDTO> saveAsset(@RequestBody AssetDTO assetDto) {
		System.out.println("stigao");
		Asset asset = AssetMapper.mapToAsset(assetDto);
		
		asset = assetService.save(asset);
		
		return new ResponseEntity<>(new AssetDTO(asset), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}", consumes = "application/json" )
	public ResponseEntity<AssetDTO> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDto) {
		Asset updatedAsset = AssetMapper.mapToAsset(assetDto);
		Asset assetToUpdate = assetService.findOne(id);
		
		if (assetToUpdate != null)
		{
			// ne sacuvavati sve ovo vec samo one promenljive vrednosti koje
			// se ustvari i menjaju
			assetToUpdate.setAddress(updatedAsset.getAddress());
			assetToUpdate.setCancelationConditions(updatedAsset.getCancelationConditions());
			assetToUpdate.setDescription(updatedAsset.getDescription());
			assetToUpdate.setName(updatedAsset.getName());
			assetToUpdate.setNumOfPeople(updatedAsset.getNumOfPeople());
			assetToUpdate.setRules(updatedAsset.getRules());
			assetService.save(assetToUpdate);
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
		AssetDTO assetDTO = new AssetDTO(asset);
		
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


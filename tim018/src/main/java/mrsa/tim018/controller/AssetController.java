package mrsa.tim018.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.service.AssetService;

@RestController
@RequestMapping(value = "/resorts")
public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AssetDTO> saveClient(@RequestBody AssetDTO assetDTO) {

		Asset asset = new Asset();
		asset.setDeleted(assetDTO.isDeleted());
		asset.setName(assetDTO.getName());
		asset.setRenter(assetDTO.getRenter());
		asset.setAddress(assetDTO.getAddress());
		asset.setDescription(assetDTO.getDescription());
		asset.setRules(assetDTO.getRules());
		asset.setNumOfPeople(assetDTO.getNumOfPeople());
		asset.setCancelationConditions(assetDTO.getCancelationConditions());
		asset.setAverageRating(assetDTO.getAverageRating());

		asset = assetService.save(asset);
		return new ResponseEntity<>(new AssetDTO(asset), HttpStatus.CREATED);
	}
}

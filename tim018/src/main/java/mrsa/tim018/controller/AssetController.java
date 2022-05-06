package mrsa.tim018.controller;

import java.util.ArrayList;
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

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.mapper.AssetMapper;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.service.AssetService;

@RestController
@CrossOrigin
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

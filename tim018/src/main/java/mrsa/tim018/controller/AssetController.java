package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import mrsa.tim018.dto.AssetsListDTO;
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
		Asset asset = AssetMapper.mapToAsset(assetDto);
		asset = assetService.save(asset);
		
		return new ResponseEntity<>(new AssetDTO(asset), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}", consumes = "application/json" )
	public ResponseEntity<AssetDTO> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDto) {
		Asset asset = AssetMapper.mapToAsset(assetDto);
		
		if (assetService.findOne(id) != null)
		{
			asset = assetService.save(asset);
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
		
		AssetsListDTO assetsList = new AssetsListDTO(assetsDTO);
		return new ResponseEntity<>(assetsDTO, HttpStatus.OK);
	}
}


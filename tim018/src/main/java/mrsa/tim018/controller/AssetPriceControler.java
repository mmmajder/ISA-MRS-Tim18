package mrsa.tim018.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetPrice;
import mrsa.tim018.service.AssetPriceService;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.utils.StringUtils;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/prices")
public class AssetPriceControler {
	@Autowired
	private AssetPriceService assetPriceService;
	
	@Autowired
	private AssetService assetService;
	
	@PreAuthorize("hasRole('BOAT_RENTER') || hasRole('FISHING_INSTRUCTOR') || hasRole('RESORT_RENTER') || hasRole('ADMIN')")
	@PostMapping("/{assetId}")
	  public ResponseEntity<AssetPrice> createNewAssetPrice(@PathVariable Long assetId, @RequestParam("newPrice") double newPrice) {
	    try {
	    	
	    	Asset a = assetService.findOne(assetId);
	    	if (a == null) 
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	
	    	LocalDate startDate = LocalDate.now().plusDays(1);
	    	if (assetPriceService.doesPriceWithStartDateExist(assetId, startDate))
	    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	    	
	    	AssetPrice oldNewestPrice = assetPriceService.getAssetPriceNullEndDate(assetId);
	    	oldNewestPrice.setEndDate(LocalDate.now());
	    	assetPriceService.save(oldNewestPrice);
	    	
	    	AssetPrice assetPrice = new AssetPrice(newPrice, startDate, assetId);
	    	assetPriceService.save(assetPrice);
	    	
	    	return new ResponseEntity<>(assetPrice, HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	    }
	  }
	
	@GetMapping("/{assetId}")
	  public ResponseEntity<AssetPrice> getAssetPrice(@PathVariable Long assetId, @RequestParam("date") String dateString) {
	    try {
	    	Asset a = assetService.findOne(assetId);
	    	if (a == null) 
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	
	    	LocalDate date = StringUtils.getDate(dateString);
	    	AssetPrice assetPrice = assetPriceService.findPriceByDate(assetId, date);
	    	return new ResponseEntity<>(assetPrice, HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@GetMapping("/today/{assetId}")
	  public ResponseEntity<AssetPrice> getTodayAssetPrice(@PathVariable Long assetId) {
	    try {
	    	Asset a = assetService.findOne(assetId);
	    	if (a == null) 
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	
	    	AssetPrice assetPrice = assetPriceService.getTodayAssetPrice(assetId);
	    	
	    	return new ResponseEntity<>(assetPrice, HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}

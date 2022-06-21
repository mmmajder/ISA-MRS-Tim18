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

import mrsa.tim018.dto.SubscriptionDTO;
import mrsa.tim018.dto.SubscriptionFetchedDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.ClientService;
import mrsa.tim018.service.SubscriptionService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/subscription")
public class SubscriptionController {

	@Autowired 
	private SubscriptionService subscriptionService;
	
	@PostMapping(value = "/subscribe")
	public ResponseEntity<SubscriptionDTO> subscribe(@RequestBody SubscriptionDTO subscriptionDto) {
		Subscription subscription = subscriptionService.subscribe(subscriptionDto);
		if(subscription!=null) {
			return new ResponseEntity<>(subscriptionDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/unsubscribe")
	public ResponseEntity<SubscriptionDTO> unsubscribe(@RequestBody SubscriptionDTO subscriptionDto) {
		Subscription subscription = subscriptionService.unsubscribe(subscriptionDto);
		if(subscription!=null) {
			return new ResponseEntity<>(subscriptionDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/hasSubscription/{assetId}/{clientId}")
	public ResponseEntity<Boolean> hasSubscription(@PathVariable Long assetId, @PathVariable Long clientId){
		Subscription subscription = subscriptionService.findOneByAssetIdAndClientId(assetId, clientId);
		if(subscription == null) {
			return new ResponseEntity<>(false, HttpStatus.OK); 
		}
		if(subscription.isDeleted()) {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PutMapping(value = "/mySubscriptions/{clientId}")
	public ResponseEntity<List<SubscriptionFetchedDTO>> getMySubscriptions(@PathVariable Long clientId, @RequestBody AssetType assetType){
		List<SubscriptionFetchedDTO> subscriptionsDto = subscriptionService.findClientsubscriptions(clientId, assetType);
		return new ResponseEntity<List<SubscriptionFetchedDTO>>(subscriptionsDto, HttpStatus.OK); 
		
	}
}
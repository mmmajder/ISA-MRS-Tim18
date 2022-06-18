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
	
	@Autowired 
	private AssetService assetService;
	
	@Autowired 
	private ClientService clientService;
	
	@PostMapping(value = "/subscribe")
	public ResponseEntity<SubscriptionDTO> subscribe(@RequestBody SubscriptionDTO subscriptionDto) {
		Asset asset = assetService.findOne(subscriptionDto.getAssetId());
		Client client = clientService.findOne(subscriptionDto.getClientId());
		Subscription subscription = subscriptionService.subscribe(asset, client);
		
		assetService.addSubscription(subscription);
		clientService.addSubscription(subscription);
		
		if(subscription!=null) {
			return new ResponseEntity<>(subscriptionDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/unsubscribe")
	public ResponseEntity<SubscriptionDTO> unsubscribe(@RequestBody SubscriptionDTO subscriptionDto) {
		Asset asset = assetService.findOne(subscriptionDto.getAssetId());
		Client client = clientService.findOne(subscriptionDto.getClientId());
		Subscription subscription = subscriptionService.unsubscribe(asset, client);
		
		assetService.removeSubscription(subscription);
		clientService.removeSubscription(subscription);
		subscription.setDeleted(true);
		subscription = subscriptionService.save(subscription);
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
		List<Subscription> subscriptions = subscriptionService.findClientsActiveSubscriptions(clientId);
		if(assetType!=AssetType.ALL)
			subscriptions = subscriptions.stream().filter(a -> a.getAsset().getAssetType() == assetType).collect(Collectors.toList());
		
		List<SubscriptionFetchedDTO> subscriptionsDto = new ArrayList<>();
		for (Subscription subscription : subscriptions) {
			SubscriptionFetchedDTO  dto = new SubscriptionFetchedDTO(subscription.getAsset(), subscription.getClient());
			subscriptionsDto.add(dto);
		}
		
		
		return new ResponseEntity<List<SubscriptionFetchedDTO>>(subscriptionsDto, HttpStatus.OK); 
		
	}
}
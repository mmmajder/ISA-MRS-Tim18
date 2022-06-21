package mrsa.tim018.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.dto.SubscriptionDTO;
import mrsa.tim018.dto.SubscriptionFetchedDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private AssetService assetService;
	@Autowired
	private ClientService clientService;

	public Subscription save(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public Subscription findOne(Long id) {
		return subscriptionRepository.findById(id).orElse(null);
	}
	public Subscription findOneByAssetIdAndClientId(Long assetId, Long clientId) {
		return subscriptionRepository.findOneByAssetIdAndClientId(assetId, clientId).orElse(null);
	}

	@Transactional
	public Subscription subscribe(SubscriptionDTO subscriptionDto) {
		Asset asset = assetService.findOne(subscriptionDto.getAssetId());
		Client client = clientService.findOne(subscriptionDto.getClientId());
		Subscription subscription = subscribe(asset, client);
		
		assetService.addSubscription(subscription);
		clientService.addSubscription(subscription);
		
		return subscription;
	}
	
	@Transactional
	public Subscription unsubscribe(SubscriptionDTO subscriptionDto) {
		Asset asset = assetService.findOne(subscriptionDto.getAssetId());
		Client client = clientService.findOne(subscriptionDto.getClientId());
		Subscription subscription = unsubscribe(asset, client);
		
		assetService.removeSubscription(subscription);
		clientService.removeSubscription(subscription);
		subscription.setDeleted(true);
		subscription = save(subscription);
		
		return subscription;
	}
	
	private Subscription subscribe(Asset asset, Client client) {
		Subscription subscription = findOneByAssetIdAndClientId(asset.getID(), client.getID());
		if(subscription == null) {
			subscription = new Subscription(asset, client);
		}
		subscription.setDeleted(false);
		save(subscription);
		return subscription;
	}
	
	private Subscription unsubscribe(Asset asset, Client client) {
		Subscription subscription = findOneByAssetIdAndClientId(asset.getID(), client.getID());
		if(subscription == null) {
			return null;
		}
		return save(subscription);
	}

	public List<Subscription> findClientsActiveSubscriptions(Long clientId) {
		return subscriptionRepository.findClientsActiveSubscriptions(clientId);
	}
	
	public List<Subscription> findAssetsActiveSubscriptions(Long clientId) {
		return subscriptionRepository.findAssetsActiveSubscriptions(clientId);
	}

	@Transactional
	public List<SubscriptionFetchedDTO> findClientsubscriptions(Long clientId, AssetType assetType) {
		List<Subscription> subscriptions = findClientsActiveSubscriptions(clientId);
		if(assetType!=AssetType.ALL)
			subscriptions = subscriptions.stream().filter(a -> a.getAsset().getAssetType() == assetType).collect(Collectors.toList());
		return map(subscriptions);
	}

	private List<SubscriptionFetchedDTO> map(List<Subscription> subscriptions) {
		List<SubscriptionFetchedDTO> subscriptionsDto = new ArrayList<>();
		for (Subscription subscription : subscriptions) {
			AssetDTO asetDTO = new AssetDTO(subscription.getAsset());
			ClientDTO clientDTO = new ClientDTO(subscription.getClient());
			SubscriptionFetchedDTO  dto = new SubscriptionFetchedDTO(asetDTO, clientDTO);
			subscriptionsDto.add(dto);
		}
		return subscriptionsDto;
	}
	
	
}
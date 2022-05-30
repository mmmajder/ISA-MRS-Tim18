package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;	

	public Subscription save(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public Subscription findOne(Long id) {
		return subscriptionRepository.findById(id).orElse(null);
	}
	public Subscription findOneByAssetIdAndClientId(Long assetId, Long clientId) {
		return subscriptionRepository.findOneByAssetIdAndClientId(assetId, clientId).orElse(null);
	}

	public Subscription subscribe(Asset asset, Client client) {
		Subscription subscription = findOneByAssetIdAndClientId(asset.getID(), client.getID());
		if(subscription == null) {
			subscription = new Subscription(asset, client);
		}
		if(subscription.isDeleted()){
			System.out.println("VEC OBRISAN?");
		}
		subscription.setDeleted(false);
		save(subscription);
		return subscription;
	}
	
	public Subscription unsubscribe(Asset asset, Client client) {
		Subscription subscription = findOneByAssetIdAndClientId(asset.getID(), client.getID());
		if(subscription == null) {
			return null;
		}
		// subscription.setDeleted(true);
		return save(subscription);
	}

	public List<Subscription> findClientsActiveSubscriptions(Long clientId) {
		return subscriptionRepository.findClientsActiveSubscriptions(clientId);
	}
	
	public List<Subscription> findAssetsActiveSubscriptions(Long clientId) {
		return subscriptionRepository.findAssetsActiveSubscriptions(clientId);
	}

}
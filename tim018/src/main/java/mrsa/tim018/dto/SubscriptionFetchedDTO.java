package mrsa.tim018.dto;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;

public class SubscriptionFetchedDTO {
	
	private Asset asset;
	private Client client;
	public SubscriptionFetchedDTO() {
		super();
	}
	public SubscriptionFetchedDTO(Asset asset, Client client) {
		super();
		this.asset = asset;
		this.client = client;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}

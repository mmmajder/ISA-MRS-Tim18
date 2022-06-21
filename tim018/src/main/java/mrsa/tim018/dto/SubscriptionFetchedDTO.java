package mrsa.tim018.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;

public class SubscriptionFetchedDTO {
	
	private AssetDTO asset;
	private ClientDTO client;
	
	public SubscriptionFetchedDTO() {
		super();
	}
	public SubscriptionFetchedDTO(AssetDTO asset, ClientDTO client) {
		super();
		this.asset = asset;
		this.client = client;
	}
	public AssetDTO getAsset() {
		return asset;
	}
	public void setAsset(AssetDTO asset) {
		this.asset = asset;
	}
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	
	
	
}

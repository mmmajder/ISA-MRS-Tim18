package models;

import java.util.Date;

public class SpecialOffer {
	
	private Long ID;
	private boolean isDeleted;
	
	private Asset asset; // renter is available from asset
	private Client client = null;
	
	private Date startDate;
	private Date endDate;
	
	private String otherServices;
	private double price;
	

	public SpecialOffer() {
	}


	public SpecialOffer(Long iD, boolean isDeleted, Asset asset, Client client, Date startDate, Date endDate,
			String otherServices, double price) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.asset = asset;
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
		this.otherServices = otherServices;
		this.price = price;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public Long getID() {
		return ID;
	}


	public Asset getAsset() {
		return asset;
	}


	public Client getClient() {
		return client;
	}


	public Date getStartDate() {
		return startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public String getOtherServices() {
		return otherServices;
	}


	public double getPrice() {
		return price;
	}
	
	
	

}

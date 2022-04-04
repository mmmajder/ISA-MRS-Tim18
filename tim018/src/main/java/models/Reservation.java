package models;

import java.util.Date;

public class Reservation {
	
	private Long ID;
	private boolean isDeleted;
	
	private Asset asset;
	private Client client;
	
	private Date startDate;
	private Date endDate;
	
	private ReservationStatus status;
	
	private Review clientReview;
	private Review assetReview;
	private Review renterReview;
	

	public Reservation() {
	}


	public Reservation(Long iD, boolean isDeleted, Asset asset, Client client, Date startDate, Date endDate,
			ReservationStatus status, Review clientReview, Review assetReview, Review renterReview) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.asset = asset;
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.clientReview = clientReview;
		this.assetReview = assetReview;
		this.renterReview = renterReview;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public ReservationStatus getStatus() {
		return status;
	}


	public void setStatus(ReservationStatus status) {
		this.status = status;
	}


	public Review getClientReview() {
		return clientReview;
	}


	public void setClientReview(Review clientReview) {
		this.clientReview = clientReview;
	}


	public Review getAssetReview() {
		return assetReview;
	}


	public void setAssetReview(Review assetReview) {
		this.assetReview = assetReview;
	}


	public Review getRenterReview() {
		return renterReview;
	}


	public void setRenterReview(Review renterReview) {
		this.renterReview = renterReview;
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


	@Override
	public String toString() {
		return "Reservation [ID=" + ID + ", isDeleted=" + isDeleted + ", asset=" + asset + ", client=" + client
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", clientReview="
				+ clientReview + ", assetReview=" + assetReview + ", renterReview=" + renterReview + "]";
	}
	
	
	

}

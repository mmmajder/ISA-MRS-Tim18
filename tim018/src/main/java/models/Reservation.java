package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column(name = "startDate", unique = true, nullable = false)
	private Date startDate;

	@Column(name = "endDate", unique = true, nullable = false)
	private Date endDate;
	
	@Column(name = "status", unique = true, nullable = false)
	private ReservationStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_review_id", unique = true, nullable = false)
	private Review clientReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_review_id", unique = true, nullable = false)
	private Review assetReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_review_id", unique = true, nullable = false)
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

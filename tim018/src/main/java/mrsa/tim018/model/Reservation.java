package mrsa.tim018.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	@JsonBackReference
	private Asset asset;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@JsonBackReference
	private Client client;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TimeRange timeRange;

	@Column(name = "status", nullable = false)
	private ReservationStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_review_id", nullable = true)
	private Review clientReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_review_id", nullable = true)
	private Review assetReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_review_id", nullable = true)
	private Review renterReview;
	

	public Reservation() {
	}
	
	public Reservation(Asset asset, Client client, TimeRange timeRange) {
		this.asset = asset;
		this.client = client;
		this.timeRange = timeRange;
		
		this.isDeleted = false;
		this.status = ReservationStatus.Pending;	
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

	public Long getID() {
		return ID;
	}


	public Asset getAsset() {
		return asset;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
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

	public void setID(Long iD) {
		ID = iD;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	

}

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
	
    @JoinColumn(name = "client_review_id", nullable = true)
	private Long clientReviewId;
	
    @JoinColumn(name = "asset_review_id", nullable = true)
	private Long assetReviewId;
	
    @JoinColumn(name = "renter_review_id", nullable = true)
	private Long renterReviewId;
	
	@Column(name = "total_price", nullable = false)
	private double totalPrice;
	
	@Column(name = "cancelation_fee", nullable = false)
	private int cancelationFee;
	

	public Reservation() {
	}
	
	public Reservation(Asset asset, Client client, TimeRange timeRange, double totalPrice) {
		this.asset = asset;
		this.client = client;
		this.timeRange = timeRange;
		this.totalPrice = totalPrice;
		
		this.isDeleted = false;
		this.status = ReservationStatus.Future;	
	}
	public Reservation(Asset asset, Client client, TimeRange timeRange) {
		this.asset = asset;
		this.client = client;
		this.timeRange = timeRange;
		
		this.isDeleted = false;
		this.status = ReservationStatus.Future;	
		this.totalPrice = 0;
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

	public Long getClientReviewId() {
		return clientReviewId;
	}

	public void setClientReviewId(Long clientReviewId) {
		this.clientReviewId = clientReviewId;
	}

	public Long getAssetReviewId() {
		return assetReviewId;
	}

	public void setAssetReviewId(Long assetReviewId) {
		this.assetReviewId = assetReviewId;
	}

	public Long getRenterReviewId() {
		return renterReviewId;
	}

	public void setRenterReviewId(Long renterReviewId) {
		this.renterReviewId = renterReviewId;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCancelationFee() {
		return cancelationFee;
	}

	public void setCancelationFee(int cancelationFee) {
		this.cancelationFee = cancelationFee;
	}
}

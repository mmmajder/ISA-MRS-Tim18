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

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TimeRange timeRange;

	@Column(name = "status", nullable = false)
	private ReservationStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_review_id", nullable = false)
	private Review clientReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_review_id", nullable = false)
	private Review assetReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_review_id", nullable = false)
	private Review renterReview;
	

	public Reservation() {
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
	
	

}

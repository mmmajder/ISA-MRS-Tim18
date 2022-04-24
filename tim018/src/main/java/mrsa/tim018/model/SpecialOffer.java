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
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private Asset asset; // renter is available from asset
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client = null;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TimeRange timeRange;
	
	@Column(name = "otherServices", nullable = false)
	private String otherServices;
	
	@Column(name = "price", nullable = false)
	private double price;
	

	public SpecialOffer() {
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

	public String getOtherServices() {
		return otherServices;
	}


	public double getPrice() {
		return price;
	}
	
	
	

}

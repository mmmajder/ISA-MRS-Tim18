package mrsa.tim018.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@Column(name = "otherServices", nullable = false)
	private String otherServices;
	
	@Column(name = "price", nullable = false)
	private double price;
	

	public SpecialOffer() {
	}


	/*public SpecialOffer(Long iD, boolean isDeleted, Asset asset, Client client, Date startDate, Date endDate,
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
	}*/


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


	/*public Client getClient() {
		return client;
	}*/


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

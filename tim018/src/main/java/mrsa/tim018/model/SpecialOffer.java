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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	@JsonBackReference
	private Asset asset; // renter is available from asset

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@JsonBackReference
	private Client client = null;

	@OneToOne(cascade = CascadeType.ALL)
	private TimeRange timeRange;

	@Column(name = "otherServices", nullable = false)
	private String otherServices;

	@Column(name = "discount", nullable = false)
	private double discount;

	public SpecialOffer(boolean isDeleted, Asset asset, Client client, TimeRange timeRange,
			String otherServices, double discount) {
		super();
		this.isDeleted = isDeleted;
		this.asset = asset;
		this.client = client;
		this.timeRange = timeRange;
		this.otherServices = otherServices;
		this.discount = discount;
	}

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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public void setOtherServices(String otherServices) {
		this.otherServices = otherServices;
	}

}

package mrsa.tim018.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Asset {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "renter_id")
	@JsonBackReference
	private Renter renter;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "assetType", nullable = false)
	private AssetType assetType;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "description", nullable = false)
	private String description;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Photo> photos;

	@Column(name = "rules", nullable = false)
	private String rules;

	@Column(name = "numOfPeople", nullable = false)
	private int numOfPeople;

	@Column(name = "cancelationConditions", nullable = false)
	private int cancelationConditions; // (0-100)

	@Column(name = "averageRating", nullable = false)
	private double averageRating;

	@Column(name = "price")
	private double price;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AssetCalendar calendar;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<PriceCatalog> prices;

	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Subscription> subscriptions = new ArrayList<>();
	
	@Version
	private Integer version;

	public Asset() {

	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public Asset(Long id, boolean isDeleted, AssetType assetType, String name, String address, String description,
			List<String> photos, String rules, int numOfPeople, int cancelationConditions, double averageRating) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.assetType = assetType;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rules = rules;
		this.numOfPeople = numOfPeople;
		this.cancelationConditions = cancelationConditions;
		this.averageRating = averageRating;
	}

	public Asset(boolean isDeleted, Renter renter, String name, AssetType assetType, String address, String description,
			List<Photo> photos, String rules, int numOfPeople, int cancelationConditions, double averageRating,
			double price, AssetCalendar calendar, List<PriceCatalog> prices) {
		super();
		this.isDeleted = isDeleted;
		this.renter = renter;
		this.name = name;
		this.assetType = assetType;
		this.address = address;
		this.description = description;
		this.photos = photos;
		this.rules = rules;
		this.numOfPeople = numOfPeople;
		this.cancelationConditions = cancelationConditions;
		this.averageRating = averageRating;
		this.price = price;
		this.calendar = calendar;
		this.prices = prices;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<PriceCatalog> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceCatalog> prices) {
		this.prices = prices;
	}

	public AssetCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(AssetCalendar calendar) {
		this.calendar = calendar;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Renter getRenter() {
		return renter;
	}

	public void setRenter(Renter renter) {
		this.renter = renter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public int getCancelationConditions() {
		return cancelationConditions;
	}

	public void setCancelationConditions(int cancelationConditions) {
		this.cancelationConditions = cancelationConditions;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public Long getID() {
		return id;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}

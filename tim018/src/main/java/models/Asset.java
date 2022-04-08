package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "renter_id")
	private Renter renter;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "address", unique = true, nullable = false)
	private String address;
	
	@Column(name = "description", unique = true, nullable = false)
	private String description;
	
	@Column(name = "photos", unique = true, nullable = false)
	private List<String> photos; // TODO: how are we going to save photos
	
	@Column(name = "rules", unique = true, nullable = false)
	private String rules;
	
	@Column(name = "numOfPeople", unique = true, nullable = false)
	private int numOfPeople;
	
	@Column(name = "cancelationConditions", unique = true, nullable = false)
	private int cancelationConditions; //(0-100)
	
	@Column(name = "averageRating", unique = true, nullable = false)
	private double averageRating;
	
	@ElementCollection
	private Map<Date, List<TimeSlot>> availability;
	
	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
	
	
	
	public Asset() {
		
	}

	public Asset(Long iD, boolean isDeleted, Renter renter, String name, String address, String description,
			List<String> photos, String rules, int numOfPeople, int cancelationConditions, double averageRating,
			HashMap<Date, List<TimeSlot>> availability, List<Reservation> reservations) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.renter = renter;
		this.name = name;
		this.address = address;
		this.description = description;
		this.photos = photos;
		this.rules = rules;
		this.numOfPeople = numOfPeople;
		this.cancelationConditions = cancelationConditions;
		this.averageRating = averageRating;
		this.availability = availability;
		this.reservations = reservations;
	}

	public boolean isDeleted() {
		return isDeleted;
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

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
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

	public Map<Date, List<TimeSlot>> getAvailability() {
		return availability;
	}

	public void setAvailability(HashMap<Date, List<TimeSlot>> availability) {
		this.availability = availability;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Long getID() {
		return ID;
	}
	
	
	
	
}

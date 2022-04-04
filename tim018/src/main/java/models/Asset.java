package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Asset {
	
	private Long ID;
	private boolean isDeleted;

	private Renter renter;
	
	private String name;
	private String address;
	private String description;
	private List<String> photo; // TODO: how are we going to save photos
	private String rules;
	private int numOfPeople;
	private int cancelationConditions; //(0-100)
	
	private double averageRating;
	
	private HashMap<Date, List<TimeSlot>> availability = new HashMap<Date, List<TimeSlot>>();
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public Asset() {
		
	}

	public Asset(Long iD, boolean isDeleted, Renter renter, String name, String address, String description,
			List<String> photo, String rules, int numOfPeople, int cancelationConditions, double averageRating,
			HashMap<Date, List<TimeSlot>> availability, List<Reservation> reservations) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.renter = renter;
		this.name = name;
		this.address = address;
		this.description = description;
		this.photo = photo;
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

	public List<String> getPhoto() {
		return photo;
	}

	public void setPhoto(List<String> photo) {
		this.photo = photo;
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

	public HashMap<Date, List<TimeSlot>> getAvailability() {
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

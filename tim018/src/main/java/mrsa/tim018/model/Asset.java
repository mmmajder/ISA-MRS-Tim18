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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
 
@Entity
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "renter_id")
	@JsonBackReference
	private Renter renter;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Photo> photos; // TODO: how are we going to save photos
	
	@Column(name = "rules", nullable = false)
	private String rules;
	
	@Column(name = "numOfPeople", nullable = false)
	private int numOfPeople;
	
	@Column(name = "cancelationConditions", nullable = false)
	private int cancelationConditions; //(0-100)
	
	@Column(name = "averageRating", nullable = false)
	private double averageRating;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AssetCalendar calendar;
	
	public Asset() {
		
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public Asset(Long id, boolean isDeleted, String name, String address, String description, List<String> photos,
			String rules, int numOfPeople, int cancelationConditions, double averageRating) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rules = rules;
		this.numOfPeople = numOfPeople;
		this.cancelationConditions = cancelationConditions;
		this.averageRating = averageRating;
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
	
}

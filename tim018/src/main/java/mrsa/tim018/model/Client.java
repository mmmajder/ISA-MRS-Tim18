package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "penaltyPoints", nullable = false)
	private int penaltyPoints;
	
//	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<SpecialOffer> specialOffers = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<>();
	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Subscription> subscriptions = new ArrayList<>();
	
	public Client() {
	}

	public Client(Long id, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, String email, String password, boolean enabled, int penaltyPoints, String verificationCode, String profilePhotoId) {
		super(id, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, email, password, enabled, verificationCode, profilePhotoId);
		this.penaltyPoints = penaltyPoints;
	}

	public Client(Long id, String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, String email, String password, int penaltyPoints, String profilePhotoId) {
	
		super(id, firstName, lastName, address, city, state, phoneNum, userType, email, password, profilePhotoId);
		this.penaltyPoints = penaltyPoints;
	}
	
	public Client(String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, String email, String password, int penaltyPoints, String profilePhotoId) {
	
		super(firstName, lastName, address, city, state, phoneNum, userType, email, password, profilePhotoId);
		this.penaltyPoints = penaltyPoints;
	}
	
	
	public Client(User user, int penaltyPoints) {
	
		super(user);
		this.penaltyPoints = penaltyPoints;
	}
	public Client(User user) {
		
		super(user);
		this.penaltyPoints = 0;
	}

	
	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}

//	public List<SpecialOffer> getSpecialOffers() {
//		return specialOffers;
//	}
//
//	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
//		this.specialOffers = specialOffers;
//	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
}

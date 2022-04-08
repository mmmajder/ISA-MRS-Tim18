package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Client extends User{

	@Column(name = "penaltyPoints", unique = true, nullable = false)
	private int penaltyPoints;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	
	public Client() {
	}

	public Client(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, UserAccount userAccount, int penaltyPoints) {
		
		super(iD, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, userAccount);
		this.penaltyPoints = penaltyPoints;
	}

	public Client(Long iD, String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, UserAccount userAccount, int penaltyPoints) {
	
		super(iD, firstName, lastName, address, city, state, phoneNum, userType, userAccount);
		this.penaltyPoints = penaltyPoints;
	}

	
	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}

	
	
}

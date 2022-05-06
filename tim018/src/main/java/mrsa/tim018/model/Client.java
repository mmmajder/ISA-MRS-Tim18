package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "penaltyPoints", nullable = false)
	private int penaltyPoints;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	
	public Client() {
	}

	public Client(Long id, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, String email, String password, boolean enabled, int penaltyPoints) {
		
		super(id, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, email, password, enabled);
		this.penaltyPoints = penaltyPoints;
	}

	public Client(Long id, String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, String email, String password, int penaltyPoints) {
	
		super(id, firstName, lastName, address, city, state, phoneNum, userType, email, password);
		this.penaltyPoints = penaltyPoints;
	}

	
	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}
}

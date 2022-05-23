package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Admin extends User {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> pendigReservations = new ArrayList<Reservation>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Registration> pendingRegistrations = new ArrayList<Registration>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> pendingReviews = new ArrayList<Review>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DeletationRequest> deletationRequests = new ArrayList<DeletationRequest>();
	
	
	public Admin() {
	}


	public Admin(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, String email, String password, boolean enabled, String verificationCode, String profilePhotoId) {
		super(iD, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, email, password, enabled, verificationCode, profilePhotoId);
	}


	public Admin(Long iD, String firstName, String lastName, String address, String city, String state, String phoneNum,
			UserType userType, String email, String password, String profilePhotoId) {
		
		super(iD, firstName, lastName, address, city, state, phoneNum, userType, email, password, profilePhotoId);
	}


	public List<Reservation> getPendigReservations() {
		return pendigReservations;
	}


	public void setPendigReservations(List<Reservation> pendigReservations) {
		this.pendigReservations = pendigReservations;
	}


	public List<Registration> getPendingRegistrations() {
		return pendingRegistrations;
	}


	public void setPendingRegistrations(List<Registration> pendingRegistrations) {
		this.pendingRegistrations = pendingRegistrations;
	}


	public List<Review> getPendingReviews() {
		return pendingReviews;
	}


	public void setPendingReviews(List<Review> pendingReviews) {
		this.pendingReviews = pendingReviews;
	}


	public List<DeletationRequest> getDeletationRequests() {
		return deletationRequests;
	}


	public void setDeletationRequests(List<DeletationRequest> deletationRequests) {
		this.deletationRequests = deletationRequests;
	}
	
	
	
}

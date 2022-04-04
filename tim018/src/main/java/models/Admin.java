package models;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

	private List<Reservation> pendigReservations = new ArrayList<Reservation>();
	private List<Registration> pendingRegistrations = new ArrayList<Registration>();
	private List<Review> pendingReviews = new ArrayList<Review>();
	private List<DeletationRequest> deletationRequests = new ArrayList<DeletationRequest>();
	
	
	public Admin() {
	}


	public Admin(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, UserAccount userAccount) {
		
		super(iD, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, userAccount);
	}


	public Admin(Long iD, String firstName, String lastName, String address, String city, String state, String phoneNum,
			UserType userType, UserAccount userAccount) {
		
		super(iD, firstName, lastName, address, city, state, phoneNum, userType, userAccount);
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

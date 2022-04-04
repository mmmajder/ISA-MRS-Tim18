package models;

public class Client extends User{

	private int penaltyPoints;
	
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

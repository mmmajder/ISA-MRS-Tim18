package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FishingInstructor extends User {

	
	private List<Asset> assets = new ArrayList<Asset>();
	private HashMap<Date, List<TimeSlot>> availability = new HashMap<Date, List<TimeSlot>>();
	
	public FishingInstructor() {
	}
	
	

	public FishingInstructor(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, UserAccount userAccount, List<Asset> assets,  HashMap<Date, List<TimeSlot>> availability) {
		
		super(iD, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, userAccount);
		this.assets = assets;
		this.availability = availability;
	}



	public FishingInstructor(Long iD, String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, UserAccount userAccount,   List<Asset> assets,  HashMap<Date, List<TimeSlot>> availability) {
		
		super(iD, firstName, lastName, address, city, state, phoneNum, userType, userAccount);
		this.assets = assets;
		this.availability = availability;
	}



	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}



	public HashMap<Date, List<TimeSlot>> getAvailability() {
		return availability;
	}

	public void setAvailability(HashMap<Date, List<TimeSlot>> availability) {
		this.availability = availability;
	}
	
	
}

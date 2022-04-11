package mrsa.tim018.dto;

import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.model.UserAccount;
import mrsa.tim018.model.UserType;

public class FishingInstructorDTO {	
	private Long id;
	private boolean isDeleted;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phoneNum;
	private UserType userType;
	private int loyaltyPoints;
	private UserAccount userAccount;
	
	public FishingInstructorDTO(Long id, boolean isDeleted, String firstName, String lastName, String address,
			String city, String state, String phoneNum, UserType userType, int loyaltyPoints, UserAccount userAccount) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.userType = userType;
		this.loyaltyPoints = loyaltyPoints;
		this.userAccount = userAccount;
	}
	
	public FishingInstructorDTO(FishingInstructor fishingInstructor) {
		this(fishingInstructor.getID(), fishingInstructor.isDeleted(), fishingInstructor.getFirstName(), fishingInstructor.getLastName(), fishingInstructor.getAddress(),
				fishingInstructor.getCity(), fishingInstructor.getState(), fishingInstructor.getPhoneNum(), fishingInstructor.getUserType(), fishingInstructor.getLoyaltyPoints(), fishingInstructor.getUserAccount());
	}

	public FishingInstructorDTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}

package models;

import java.util.Objects;

public class User {
	
	private Long ID;
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
	
	public User() {
		
	}

	public User(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, UserAccount userAccount) {
		super();
		ID = iD;
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

	public User(Long iD, String firstName, String lastName, String address, String city, String state, String phoneNum,
			UserType userType, UserAccount userAccount) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.userType = userType;
		this.userAccount = userAccount;
		
		this.loyaltyPoints = 0;
		this.isDeleted = false;
	}
	public Long getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public UserType getUserType() {
		return userType;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", phoneNum=" + phoneNum + ", userType=" + userType
				+ ", loyaltyPoints=" + loyaltyPoints + ", userAccount=" + userAccount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, address, city, firstName, lastName, loyaltyPoints, phoneNum, state, userAccount,
				userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(ID, other.ID);
	}
	
	
	
	
	
	
}

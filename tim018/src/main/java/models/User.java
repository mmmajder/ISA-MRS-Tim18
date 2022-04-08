package models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
	
	@Id
	@SequenceGenerator(name="userSeqGen", sequenceName = "Seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
	private Long ID;
	
	@Column(name="isDeleted", unique=true, nullable=false)
	private boolean isDeleted;
	
	
	@Column(name="firstName", unique=true, nullable=false)
	private String firstName;
	
	@Column(name="lastName", unique=true, nullable=false)
	private String lastName;
	
	@Column(name="address", unique=true, nullable=false)
	private String address;
	
	@Column(name="city", unique=true, nullable=false)
	private String city;
	
	@Column(name="state", unique=true, nullable=false)
	private String state;
	
	@Column(name="phoneNum", unique=true, nullable=false)
	private String phoneNum;
	
	@Column(name="userType", unique=true, nullable=false)
	private UserType userType;
	
	@Column(name="loyaltyPoints", unique=true, nullable=false)
	private int loyaltyPoints;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "ID")
	private UserAccount userAccount;
	
	@OneToOne(mappedBy = "user")
	private Registration registration;
	
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

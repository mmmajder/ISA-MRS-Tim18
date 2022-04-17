package mrsa.tim018.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "\"user\"")
public class User {
	@Id
	@SequenceGenerator(name="userSeqGen", sequenceName = "Seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
	private Long id;
	
	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	
	@Column(name="firstName", nullable=false)
	private String firstName;
	
	@Column(name="lastName", nullable=false)
	private String lastName;
	
	@Column(name="address", nullable=false)
	private String address;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="state", nullable=false)
	private String state;
	
	@Column(name="phoneNum", nullable=false)
	private String phoneNum;
	
	@Enumerated(EnumType.STRING)
	@Column(name="userType", nullable=false)
	private UserType userType;
	
	@Column(name="loyaltyPoints", nullable=false)
	private int loyaltyPoints;
	
	@OneToOne
	private UserAccount userAccount;
	
	public User() {
		
	}

	public User(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, UserAccount userAccount) {
		super();
		this.id = iD;
		this.isDeleted = isDeleted;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.userType = userType;
		this.loyaltyPoints = loyaltyPoints;
	}
	

	public User(Long id, String firstName, String lastName, String address, String city, String state, String phoneNum,
			UserType userType, UserAccount userAccount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.userType = userType;
	//	this.userAccount = userAccount;
		
		this.loyaltyPoints = 0;
		this.isDeleted = false;
	}	
	
	public Long getID() {
		return id;
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

	/*public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}*/

	/*@Override
	public String toString() {
		return "User [ID=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", phoneNum=" + phoneNum + ", userType=" + userType
				+ ", loyaltyPoints=" + loyaltyPoints + ", userAccount=" + userAccount + "]";
	}*/

	@Override
	public String toString() {
		return "User [ID=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", phoneNum=" + phoneNum + ", userType=" + userType
				+ ", loyaltyPoints=" + loyaltyPoints + ", userAccount=" + "]";
	}

	
	/*@Override
	public int hashCode() {
		return Objects.hash(id, address, city, firstName, lastName, loyaltyPoints, phoneNum, state, userAccount,
				userType);
	}*/

	@Override
	public int hashCode() {
		return Objects.hash(id, address, city, firstName, lastName, loyaltyPoints, phoneNum, state,
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
		return Objects.equals(id, other.id);
	}
	
}

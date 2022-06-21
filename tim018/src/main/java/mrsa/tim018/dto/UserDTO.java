package mrsa.tim018.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.Role;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;

public class UserDTO {
	
	public String firstName;
	public String lastName;
	public Long id;
	public boolean isDeleted;
	public String address;
	public String city;
	public String state;
	public String phoneNum;
	public UserType userType;
	public int loyaltyPoints;
	public boolean enabled;
	public Timestamp lastPasswordResetDate;
	public String profilePhotoId;
    
	public String email;
	//public String password;
	//public String verificationCode;
	public DeletationRequest deletationRequest;
	public List<Role> roles;
	
	public UserDTO(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.id = user.getID();
		this.isDeleted = user.isDeleted();
		this.address = user.getAddress();
		this.city = user.getCity();
		this.state = user.getState();
		this.phoneNum = user.getPhoneNum();
		this.userType = user.getUserType();
		this.loyaltyPoints = user.getLoyaltyPoints();
		this.enabled = user.isEnabled();
		this.lastPasswordResetDate = user.getLastPasswordResetDate();
		this.profilePhotoId = user.getProfilePhotoId();
		this.email = user.getEmail();
		this.deletationRequest = user.getDeletationRequest();
		this.roles = user.getRoles();
	}
}

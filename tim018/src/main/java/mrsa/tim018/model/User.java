package mrsa.tim018.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Users")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

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

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
    
    @Column(name = "profilePhotoId")
    private String profilePhotoId;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
	
	/*@OneToOne
	private UserAccount userAccount;*/
    @Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private DeletationRequest deletationRequest;
	
	@Column(name="biography")
	private String biography;
	
	@Column(name = "verification_code", length = 64)
    private String verificationCode;
	
	public User() {
		
	}

	/*public User(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
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
		//this.userAccount = userAccount;
		
		this.loyaltyPoints = 0;
		this.isDeleted = false;
		this.enabled = true;
	}*/
	public User(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, String email, String password, boolean enabled, String verificationCode, String profilePhotoId) {
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
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.verificationCode = verificationCode;
		this.profilePhotoId = profilePhotoId;
	}
	

	public User(Long id, String firstName, String lastName, String address, String city, String state, String phoneNum,
			UserType userType, String email, String password, String profilePhotoId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.userType = userType;
		this.email = email;
		this.password = password;
		this.profilePhotoId = profilePhotoId;
		
		this.loyaltyPoints = 0;
		this.isDeleted = false;
		this.enabled = false;
	}
	
	public User(User user) {
		super();
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.address = user.address;
		this.city = user.city;
		this.state = user.state;
		this.phoneNum = user.phoneNum;
		this.userType = user.userType;
		this.email = user.email;
		this.password = user.password;
		this.verificationCode = user.verificationCode;
		this.profilePhotoId = user.profilePhotoId;
		this.roles = user.roles;
		this.loyaltyPoints = 0;
		this.isDeleted = false;
		this.enabled = false;
	}
	
	
	public String getProfilePhotoId() {
		return profilePhotoId;
	}

	public void setProfilePhotoId(String profilePhotoId) {
		this.profilePhotoId = profilePhotoId;
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

	/*public UserAccount getUserAccount() {
		return userAccount;
	}*/

	
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
	
	@JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public List<Role> getRoles() {
       return roles;
    }
    

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
	
	@Override
	public String toString() {
		return "User [ID=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", phoneNum=" + phoneNum + ", userType=" + userType
				+ ", loyaltyPoints=" + loyaltyPoints + ", userAccount=" + "]";
	}

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

/*	@Override
	public String getPassword() {
		return userAccount.getPassword();
	}

	@Override
	public String getUsername() {
		return userAccount.getEmail();
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}*/
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DeletationRequest getDeletationRequest() {
		return deletationRequest;
	}

	public void setDeletationRequest(DeletationRequest deletationRequest) {
		this.deletationRequest = deletationRequest;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	
}

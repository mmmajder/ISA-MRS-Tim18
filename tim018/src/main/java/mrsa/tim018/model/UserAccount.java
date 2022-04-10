package mrsa.tim018.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", unique = true, nullable = false)
	private String password;
	
	//@OneToOne(mappedBy = "userAccount")
	
	/*@OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
	private User user;*/
		
	public UserAccount() {
	}

	
	public UserAccount(Long iD, boolean isDeleted, String email, String password) {
		super();
		id = iD;
		this.isDeleted = isDeleted;
		this.email = email;
		this.password = password;
	}
	
	
	public UserAccount(Long iD, String email, String password) {
		super();
		id = iD;
		this.email = email;
		this.password = password;
		this.isDeleted = false;
	}


	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getID() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, isDeleted, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(id, other.id) && Objects.equals(email, other.email) && isDeleted == other.isDeleted
				&& Objects.equals(password, other.password);
	}

	

}

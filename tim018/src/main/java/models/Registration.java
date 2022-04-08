package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long ID;
	
	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;
	
	@Column(name = "isDeleted", unique = true, nullable = false)
	private RequestStatus status;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
	private User user;
	
	public Registration() {
	}

	public Registration(Long iD, boolean isDeleted, RequestStatus status, User user) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.status = status;
		this.user = user;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Long getID() {
		return ID;
	}

	public User getUser() {
		return user;
	}
	
	

}

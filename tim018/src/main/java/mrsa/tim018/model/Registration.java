package mrsa.tim018.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;
	
	@Column(name = "status", unique = true, nullable = false)
	private RequestStatus status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Registration() {
	}

	public Registration(Long iD, boolean isDeleted, RequestStatus status, User user) {
		super();
		id = iD;
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
		return id;
	}

	public User getUser() {
		return user;
	}
}

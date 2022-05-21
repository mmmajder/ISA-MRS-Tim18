package mrsa.tim018.model;

import java.time.LocalDateTime;

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

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "status", nullable = false)
	private RequestStatus status;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	@Column(name = "registrationDateTime", nullable = false)
	private LocalDateTime registrationDateTime;

	public Registration() {
	}

	public Registration(Long id, boolean isDeleted, RequestStatus status, User user,
			LocalDateTime registrationDateTime) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.status = status;
		this.user = user;
		this.registrationDateTime = registrationDateTime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getRegistrationDateTime() {
		return registrationDateTime;
	}

	public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}

	public void setUser(User user) {
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

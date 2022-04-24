package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DeletationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "status", nullable = false)
	private RequestStatus status;
	
	@Column(name = "reason", nullable = false)
	private String reason;

	@OneToOne
	private User user;

	public DeletationRequest() {
		// TODO Auto-generated constructor stub
	}

	public DeletationRequest(Long iD, boolean isDeleted, RequestStatus status, User user, String reason) {
		super();
		id = iD;
		this.isDeleted = isDeleted;
		this.status = status;
		this.user = user;
		this.reason = reason;
	}
	public DeletationRequest(User user, String reason) {
		super();
		this.isDeleted = false;
		this.status = RequestStatus.Pending;
		this.user = user;
		this.reason = reason;
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
	public User getUser() {
		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

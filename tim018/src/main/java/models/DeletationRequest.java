package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DeletationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "status", nullable = false)
	private RequestStatus status;

	@Column(name = "user", nullable = false)
	private User user;

	public DeletationRequest() {
		// TODO Auto-generated constructor stub
	}

	public DeletationRequest(Long iD, boolean isDeleted, RequestStatus status, User user) {
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

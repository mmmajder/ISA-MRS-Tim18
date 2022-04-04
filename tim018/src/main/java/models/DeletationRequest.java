package models;

public class DeletationRequest {
	
	private Long ID;
	private boolean isDeleted;
	
	private RequestStatus status;
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

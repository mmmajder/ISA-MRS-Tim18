package models;

public class Registration {

	private Long ID;
	private boolean isDeleted;
	
	private RequestStatus status;
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

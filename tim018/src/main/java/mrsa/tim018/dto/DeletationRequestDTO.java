package mrsa.tim018.dto;

import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;

public class DeletationRequestDTO {

	private Long id;
	private boolean isDeleted;
	private RequestStatus status;
	private String reason;
	private User user;
	
	public DeletationRequestDTO() {
	}

	public DeletationRequestDTO(Long id, boolean isDeleted, RequestStatus status, String reason, User user) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.status = status;
		this.reason = reason;
		this.user = user;
	}
	public DeletationRequestDTO(DeletationRequest deletationRequest) {
		super();
		this.id = deletationRequest.getId();
		this.isDeleted = deletationRequest.isDeleted();
		this.status = deletationRequest.getStatus();
		this.reason = deletationRequest.getReason();
		this.user = deletationRequest.getUser();
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


package mrsa.tim018.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@OneToOne()
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

	@Override
	public int hashCode() {
		return Objects.hash(id, isDeleted, reason, status, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeletationRequest other = (DeletationRequest) obj;
		return Objects.equals(id, other.id) && isDeleted == other.isDeleted && Objects.equals(reason, other.reason)
				&& status == other.status && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "DeletationRequest [id=" + id + ", isDeleted=" + isDeleted + ", status=" + status + ", reason=" + reason
				+ ", user=" + user + "]";
	}
	
	
	
}

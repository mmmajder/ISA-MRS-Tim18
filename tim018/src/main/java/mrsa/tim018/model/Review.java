package mrsa.tim018.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;

	@Column(name = "text", unique = true, nullable = false)
	private String text;
	
	@Column(name = "rating", unique = true, nullable = false)
	private int rating;
	
	@Column(name = "isComplaint", unique = true, nullable = false)
	private boolean isComplaint;	// TODO: add option in review when client doesn't show up
	
	@Column(name = "senderID", unique = true, nullable = false)
	private Long senderID;
	
	@Column(name = "recieverID", unique = true, nullable = false)
	private Long recieverID;
	
	@Column(name = "status", unique = true, nullable = false)
	private RequestStatus status;
	
	public Review() {
	}

	public Review(Long iD, boolean isDeleted, String text, int rating, boolean isComplaint, Long senderID, Long recieverID,
			RequestStatus status) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.text = text;
		this.rating = rating;
		this.isComplaint = isComplaint;
		this.senderID = senderID;
		this.recieverID = recieverID;
		this.status = status;
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

	public String getText() {
		return text;
	}

	public int getRating() {
		return rating;
	}

	public boolean isComplaint() {
		return isComplaint;
	}

	public Long getSenderID() {
		return senderID;
	}

	public Long getRecieverID() {
		return recieverID;
	}

	@Override
	public String toString() {
		return "Review [ID=" + ID + ", isDeleted=" + isDeleted + ", text=" + text + ", rating=" + rating
				+ ", isComplaint=" + isComplaint + ", senderID=" + senderID + ", recieverID=" + recieverID + ", status="
				+ status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, isComplaint, isDeleted, rating, recieverID, senderID, status, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(ID, other.ID) && isComplaint == other.isComplaint && isDeleted == other.isDeleted
				&& rating == other.rating && Objects.equals(recieverID, other.recieverID)
				&& Objects.equals(senderID, other.senderID) && status == other.status
				&& Objects.equals(text, other.text);
	}

	
	

}

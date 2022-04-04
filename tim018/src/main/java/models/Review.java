package models;

import java.util.Objects;

public class Review {
	
	private Long ID;
	private boolean isDeleted;

	private String text;
	private int rating;
	private boolean isComplaint;	// TODO: add option in review when client doesn't show up
	
	private Long senderID;
	private Long recieverID;
	
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

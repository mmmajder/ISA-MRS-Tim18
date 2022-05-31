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
	
	@Column(name = "isDeleted")
	private boolean isDeleted;

	@Column(name = "text")
	private String text;
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "isComplaint")
	private boolean isComplaint;	// TODO: add option in review when client doesn't show up
	
	@Column(name = "didntShowUp")
	private boolean didntShowUp;
	
	@Column(name = "isClientWriting")
	private boolean isClientWriting;
	
	@Column(name = "clientID")
	private Long clientID;
	
	@Column(name = "renterID")
	private Long renterID;
	
	@Column(name = "assetId")
	private Long assetId;
	
	@Column(name = "status")
	private RequestStatus status;
	
	@Column(name = "reservationId")
	private Long reservationId;
	
	public Review() {
	}

	public Review(Long iD, boolean isDeleted, String text, int rating, boolean isComplaint, boolean didntShowUp, boolean isClientWriting, Long clientID, Long renterID,
			Long assetId, RequestStatus status, Long reservationId) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.text = text;
		this.rating = rating;
		this.isComplaint = isComplaint;
		this.didntShowUp = didntShowUp;
 		this.clientID = clientID;
		this.renterID = renterID;
		this.status = status;
		this.assetId = assetId;
		this.isClientWriting = isClientWriting;
		this.reservationId = reservationId;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isComplaint() {
		return isComplaint;
	}

	public void setComplaint(boolean isComplaint) {
		this.isComplaint = isComplaint;
	}

	public boolean isClientWriting() {
		return isClientWriting;
	}

	public void setClientWriting(boolean isClientWriting) {
		this.isClientWriting = isClientWriting;
	}

	public Long getClientID() {
		return clientID;
	}

	public void setClientID(Long clientID) {
		this.clientID = clientID;
	}

	public Long getRenterID() {
		return renterID;
	}

	public void setRenterID(Long renterID) {
		this.renterID = renterID;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public boolean isDidntShowUp() {
		return didntShowUp;
	}

	public void setDidntShowUp(boolean didntShowUp) {
		this.didntShowUp = didntShowUp;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
}

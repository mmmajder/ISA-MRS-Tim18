package mrsa.tim018.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.TimeRange;

public class ReservationDTO {
	
	private Long id;
	
	// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Long asset;
	
	private Long clientId;
	
	private TimeRange timeRange;
	
	private boolean isCancelable;
	private Long duration;
	private boolean isReviewable;
	private double totalPrice;
	
	private ReservationStatus reservationStatus;
	
	private Long assetReviewId;
	private Long renterReviewId;
	private Long clientReviewId;
	
	public ReservationDTO() {
	}
	
	public ReservationDTO(Reservation reservation, boolean cancelable, Long duration, double totalPrice) {
		this.id = reservation.getID();
		this.asset = reservation.getAsset().getID();

		this.clientId = reservation.getClient().getID();
		
		this.timeRange = reservation.getTimeRange();
		this.isCancelable = cancelable;
		this.duration = duration;
		this.reservationStatus = reservation.getStatus();
		this.totalPrice = totalPrice;
	}

	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getID();
		this.asset = reservation.getAsset().getID();

		this.clientId = reservation.getClient().getID();
		this.timeRange = reservation.getTimeRange();
		this.reservationStatus = reservation.getStatus();
		this.totalPrice = reservation.getTotalPrice();
		this.assetReviewId = reservation.getAssetReviewId();
		this.renterReviewId = reservation.getRenterReviewId();
		this.clientReviewId = reservation.getClientReviewId();
		
		this.isCancelable = false;
		this.isReviewable = false;
	}
	
	public Long getAsset() {
		return asset;
	}

	public void setAsset(Long asset) {
		this.asset = asset;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}


	public boolean isCancelable() {
		return isCancelable;
	}

	public void setCancelable(boolean cancelable) {
		this.isCancelable = cancelable;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public boolean isReviewable() {
		return isReviewable;
	}

	public void setReviewable(boolean isReviewable) {
		this.isReviewable = isReviewable;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getAssetReviewId() {
		return assetReviewId;
	}

	public void setAssetReviewId(Long assetReviewId) {
		this.assetReviewId = assetReviewId;
	}

	public Long getRenterReviewId() {
		return renterReviewId;
	}

	public void setRenterReviewId(Long renterReviewId) {
		this.renterReviewId = renterReviewId;
	}

	public Long getClientReviewId() {
		return clientReviewId;
	}

	public void setClientReviewId(Long clientReviewId) {
		this.clientReviewId = clientReviewId;
	}
	
	
}
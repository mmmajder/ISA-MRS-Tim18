package mrsa.tim018.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.TimeRange;

public class ReservationDTO {
	
	private Long id;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Asset asset;
	
	private Long clientId;
	//private Client client;
	
	private TimeRange timeRange;
	
	private boolean isCancelable;
	private Long duration;
	private boolean isReviewable;
	
	private ReservationStatus reservationStatus;
	
	public ReservationDTO() {
	}
	
	public ReservationDTO(Reservation reservation, boolean cancelable, Long duration) {
		this.id = reservation.getID();
		this.asset = reservation.getAsset();
		
		//this.client = reservation.getClient();
		this.clientId = reservation.getClient().getID();
		
		this.timeRange = reservation.getTimeRange();
		this.isCancelable = cancelable;
		this.duration = duration;
		this.reservationStatus = reservation.getStatus();
	}

	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getID();
		this.asset = reservation.getAsset();
		
		//this.client = reservation.getClient();
		this.clientId = reservation.getClient().getID();
		this.timeRange = reservation.getTimeRange();
		this.reservationStatus = reservation.getStatus();
		
		this.isCancelable = false;
		this.isReviewable = false;
	}
	
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
//
//	public Client getClient() {
//		return client;
//	}
//
//	public void setClient(Client client) {
//		this.client = client;
//	}

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
	
	
}
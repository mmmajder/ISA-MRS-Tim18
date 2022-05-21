package mrsa.tim018.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.TimeRange;

public class ReservationDTO {

	private Long assetId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Asset asset;
	
	private Long clientId;
	//private Client client;
	
	private TimeRange timeRange;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	
	private boolean isCancelable;
	
	public ReservationDTO() {
	}
	
	public ReservationDTO(Reservation reservation, boolean cancelable) {
		this.asset = reservation.getAsset();
		this.assetId = reservation.getAsset().getID();
		
		//this.client = reservation.getClient();
		this.clientId = reservation.getClient().getID();
		
		this.timeRange = reservation.getTimeRange();
		this.fromDateTime = reservation.getTimeRange().getFromDateTime();
		this.toDateTime = reservation.getTimeRange().getToDateTime();
		
		this.isCancelable = cancelable;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
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

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public boolean isCancelable() {
		return isCancelable;
	}

	public void setCancelable(boolean cancelable) {
		this.isCancelable = cancelable;
	}
	
	
	
	
	
}

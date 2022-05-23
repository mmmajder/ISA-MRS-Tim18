package mrsa.tim018.dto;

import java.time.LocalDateTime;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Renter;

public class SpecialOfferReservationDTO {
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private double price;
	private long clientId;
	private long assetId;

	public SpecialOfferReservationDTO(LocalDateTime startDateTime, LocalDateTime endDateTime, double price,
			long renterId, long assetId) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.price = price;
		this.assetId = assetId;
		this.clientId = clientId;
	}

	public SpecialOfferReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

}

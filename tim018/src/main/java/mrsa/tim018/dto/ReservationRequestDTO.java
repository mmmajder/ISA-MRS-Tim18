package mrsa.tim018.dto;

import java.time.LocalDateTime;

public class ReservationRequestDTO {
	
	private Long assetId;
	private Long clientId;

	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;

	private int numOfPeople;
	
	public ReservationRequestDTO() {
	}
	
	public ReservationRequestDTO(Long assetId, Long clientId, LocalDateTime fromDateTime, LocalDateTime toDateTime, int numOfPeople) {
		this.assetId = assetId;
		this.clientId =clientId;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.numOfPeople = numOfPeople;
	}
	
	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
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

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}	
	
}

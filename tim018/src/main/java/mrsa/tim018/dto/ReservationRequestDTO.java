package mrsa.tim018.dto;

import java.time.LocalDateTime;

public class ReservationRequestDTO {
	
	private Long assetId;
	private Long clientId;

	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;

	private int numOfPeople;
	private double totalPrice;
	
	public ReservationRequestDTO() {
	}
	
	public ReservationRequestDTO(Long assetId, Long clientId, LocalDateTime fromDateTime, LocalDateTime toDateTime, int numOfPeople, double totalPrice) {
		this.assetId = assetId;
		this.clientId =clientId;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.numOfPeople = numOfPeople;
		this.totalPrice = totalPrice;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}	
	
	
}

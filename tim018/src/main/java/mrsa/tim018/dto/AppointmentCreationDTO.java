package mrsa.tim018.dto;

import mrsa.tim018.model.AppointmentType;

public class AppointmentCreationDTO {
	private AppointmentType type;
	private String fromDateTime;
	private String toDateTime;
	private String offerUntil;
	private int discount;
	private Long userId;
	private Long assetId;
	private boolean isPattern;

	public AppointmentCreationDTO(AppointmentType type, String fromDateTime, String toDateTime, String offerUntil,
			int discount, Long userId, Long assetId) {
		super();
		this.type = type;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.offerUntil = offerUntil;
		this.discount = discount;
		this.userId = userId;
		this.assetId = assetId;
	}

	public AppointmentCreationDTO(AppointmentType type, String fromDateTime, String toDateTime, Long userId,
			Long assetId) {
		super();
		this.type = type;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.userId = userId;
		this.assetId = assetId;
	}

	public String getOfferUntil() {
		return offerUntil;
	}

	public void setOfferUntil(String offerUntil) {
		this.offerUntil = offerUntil;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isPattern() {
		return isPattern;
	}

	public String getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(String fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public String getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(String toDateTime) {
		this.toDateTime = toDateTime;
	}

	public void setPattern(boolean isPattern) {
		this.isPattern = isPattern;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AppointmentCreationDTO() {
		super();
	}

}

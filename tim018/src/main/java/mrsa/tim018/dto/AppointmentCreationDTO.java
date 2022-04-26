package mrsa.tim018.dto;

import java.time.LocalDateTime;

import mrsa.tim018.model.AppointmentType;

public class AppointmentCreationDTO {
	private AppointmentType type;
	private String fromDateTime;
	private String toDateTime;
	private Long userId;
	private Long assetId;
	private boolean isPattern;

	public AppointmentCreationDTO(AppointmentType type, String fromDateTime, String toDateTime,
			Long userId, Long assetId, boolean isPattern) {
		super();
		this.type = type;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.userId = userId;
		this.assetId = assetId;
		this.isPattern = isPattern;
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

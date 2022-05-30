package mrsa.tim018.dto;

public class SubscriptionDTO {

	private Long assetId;
	private Long clientId;

	public SubscriptionDTO(Long assetId, Long clientId) {
		super();
		this.assetId = assetId;
		this.clientId = clientId;
	}

	public SubscriptionDTO() {
		super();
		// TODO Auto-generated constructor stub
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
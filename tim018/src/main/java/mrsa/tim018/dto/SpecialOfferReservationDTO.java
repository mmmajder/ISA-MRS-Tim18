package mrsa.tim018.dto;

public class SpecialOfferReservationDTO {
	private long specialOfferId;
	private long assetId;
	private long clientId;

	public SpecialOfferReservationDTO(long specialOfferId, long assetId, long clientId) {
		super();
		this.specialOfferId = specialOfferId;
		this.assetId = assetId;
		this.clientId = clientId;
	}

	public SpecialOfferReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getSpecialOfferId() {
		return specialOfferId;
	}

	public void setSpecialOfferId(long specialOfferId) {
		this.specialOfferId = specialOfferId;
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

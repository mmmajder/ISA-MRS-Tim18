package mrsa.tim018.dto;

public class AddPointDTO {
	private long id;
	private long assetId;
	private long clientID;
	private long renterID;
	private String text;

	public AddPointDTO(long id, long assetId, long clientID, long renterID, String text) {
		super();
		this.id = id;
		this.assetId = assetId;
		this.clientID = clientID;
		this.renterID = renterID;
		this.text = text;
	}

	public AddPointDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public long getRenterID() {
		return renterID;
	}

	public void setRenterID(long renterID) {
		this.renterID = renterID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

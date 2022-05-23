package mrsa.tim018.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Image {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Lob
	private byte[] data;
	private boolean isDeleted;
	private String type;
	private Long assetId;
	
	public Image() {}
	
	public Image(byte[] data, String type) {
		this.data = data;
		this.type = type;
		this.isDeleted = false;
	}
	
	public Image(byte[] data, String type, Long assetId) {
		this(data, type);
		this.assetId = assetId;
	}
	
	public Image(byte[] data, String type, boolean isDeteled, Long assetId) {
		this(data, type, assetId);
		this.isDeleted = isDeteled;
	}
	
	public Image(byte[] data, Long assetId) {
		this.data = data;
		this.type = null;
		this.assetId = assetId;
		this.isDeleted = false;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
}

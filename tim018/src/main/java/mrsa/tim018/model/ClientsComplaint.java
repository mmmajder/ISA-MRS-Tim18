package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ClientsComplaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "text", nullable = false)
	private String text;

	@Column(name = "sender", nullable = false)
	private long sender;

	@Column(name = "receiverClientsComplaint", nullable=true)
	private long receiverClientsComplaint;

	@Column(name = "assetId" , nullable=true)
	private long assetId;

	public ClientsComplaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientsComplaint(Long iD, boolean isDeleted, String text, long sender, long receiverClientsComplaint,
			long assetId) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.text = text;
		this.sender = sender;
		this.receiverClientsComplaint = receiverClientsComplaint;
		this.assetId = assetId;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getSender() {
		return sender;
	}

	public void setSender(long sender) {
		this.sender = sender;
	}

	public long getReceiverClientsComplaint() {
		return receiverClientsComplaint;
	}

	public void setReceiverClientsComplaint(long receiverClientsComplaint) {
		this.receiverClientsComplaint = receiverClientsComplaint;
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

}

package mrsa.tim018.dto;

import javax.persistence.Column;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.ClientsComplaint;
import mrsa.tim018.model.Renter;

public class ClientsComplaintDTO {
	private boolean isDeleted;
	private String text;
	private Client sender;
	private Renter receiverClientsComplaint;
	private Asset receiverAssetClientsComplaint;

	public ClientsComplaintDTO(boolean isDeleted, String text, Client sender, Renter receiverClientsComplaint,
			Asset receiverAssetClientsComplaint) {
		super();
		this.isDeleted = isDeleted;
		this.text = text;
		this.sender = sender;
		this.receiverClientsComplaint = receiverClientsComplaint;
		this.receiverAssetClientsComplaint = receiverAssetClientsComplaint;
	}
	
	/*public ClientsComplaintDTO(ClientsComplaint clientsComplaint) {
		this.isDeleted = clientsComplaint.isDeleted();
		this.text = clientsComplaint.getText();
		this.sender = clientsComplaint.getSender();
		this.receiverClientsComplaint = clientsComplaint.getReceiverClientsComplaint();
		this.receiverAssetClientsComplaint = clientsComplaint.getReceiverAssetClientsComplaint();

	}*/

	public ClientsComplaintDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public Client getSender() {
		return sender;
	}

	public void setSender(Client sender) {
		this.sender = sender;
	}

	public Renter getReceiverClientsComplaint() {
		return receiverClientsComplaint;
	}

	public void setReceiverClientsComplaint(Renter receiverClientsComplaint) {
		this.receiverClientsComplaint = receiverClientsComplaint;
	}

	public Asset getReceiverAssetClientsComplaint() {
		return receiverAssetClientsComplaint;
	}

	public void setReceiverAssetClientsComplaint(Asset receiverAssetClientsComplaint) {
		this.receiverAssetClientsComplaint = receiverAssetClientsComplaint;
	}

}

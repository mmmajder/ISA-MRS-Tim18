package mrsa.tim018.dto;

import javax.persistence.Column;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Resort;

public class ResortDTO extends AssetDTO {
	private int numberOfBeds;
	private int numberOfRooms;
	
	public ResortDTO() {}
	
	public ResortDTO(Resort resort) {
		super(resort.getID(), resort.getAssetType(), resort.isDeleted(), resort.getRenter(), resort.getName(),
				resort.getAddress(), resort.getDescription(), resort.getRules(), resort.getNumOfPeople(),
				resort.getCancelationConditions(), resort.getAverageRating(), resort.getPrice());
		this.numberOfBeds = resort.getNumberOfBeds();
		this.numberOfRooms =  resort.getNumberOfRooms();
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}
}

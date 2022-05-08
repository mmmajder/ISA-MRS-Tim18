package mrsa.tim018.dto;

import mrsa.tim018.model.Boat;

public class BoatDTO extends AssetDTO {
	private String boatType;
	private int length;
	private int numOfMotor;
	private int motorPower;
	private int maxSpeed;
	private String navigationEquipment;
	private String fishingEquipment;
	
	public BoatDTO(Boat boat) {
		super(boat.getID(), boat.getAssetType(), boat.isDeleted(), boat.getRenter(), boat.getName(),
				boat.getAddress(), boat.getDescription(), boat.getRules(), boat.getNumOfPeople(),
				boat.getCancelationConditions(), boat.getAverageRating(), boat.getPrice());
		this.boatType = boat.getBoatType();
		this.length =  boat.getLength();
		this.numOfMotor =  boat.getNumOfMotor();
		this.motorPower =  boat.getMotorPower();
		this.maxSpeed =  boat.getMaxSpeed();
		this.navigationEquipment =  boat.getNavigationEquipment();
		this.fishingEquipment =  boat.getFishingEquipment();
	}

	public String getBoatType() {
		return boatType;
	}

	public int getLength() {
		return length;
	}

	public int getNumOfMotor() {
		return numOfMotor;
	}

	public int getMotorPower() {
		return motorPower;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}
}

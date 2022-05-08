package mrsa.tim018.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Boat extends Asset {
	@Column(name = "boatType")
	private String boatType;

	@Column(name = "length")
	private int length;

	@Column(name = "numOfMotor")
	private int numOfMotor;

	@Column(name = "motorPower")
	private int motorPower;

	@Column(name = "maxSpeed")
	private int maxSpeed;

	@Column(name = "navigationEquipment")
	private String navigationEquipment;

	@Column(name = "fishingEquipment")
	private String fishingEquipment;

	public Boat() {
		super();
	}

	public Boat(Long id, boolean isDeleted, AssetType assetType, String name, String address, String description, List<String> photos,
			String rules, int numOfPeople, int cancelationConditions, double averageRating) {
		super(id, isDeleted, assetType, name,  address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
	}

	public Boat(Long id, boolean isDeleted, AssetType assetType, String name, String address, String description, List<String> photos,
			String rules, int numOfPeople, int cancelationConditions, double averageRating, String boatType, int length, int numOfMotor, int motorPower, int maxSpeed,
			String navigationEquipment, String fishingEquipment) {
		super(id, isDeleted, assetType, name, address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
		this.boatType = boatType;
		this.length = length;
		this.numOfMotor = numOfMotor;
		this.motorPower = motorPower;
		this.maxSpeed = maxSpeed;
		this.navigationEquipment = navigationEquipment;
		this.fishingEquipment = fishingEquipment;
	}

	public String getBoatType() {
		return boatType;
	}

	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNumOfMotor() {
		return numOfMotor;
	}

	public void setNumOfMotor(int numOfMotor) {
		this.numOfMotor = numOfMotor;
	}

	public int getMotorPower() {
		return motorPower;
	}

	public void setMotorPower(int motorPower) {
		this.motorPower = motorPower;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(String navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}
}

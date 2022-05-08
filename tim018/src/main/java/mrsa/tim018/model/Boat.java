package mrsa.tim018.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Boat extends Asset {
	@Column(name = "type")
	private String type;

	@Column(name = "length")
	private String length;

	@Column(name = "numOfMotor")
	private String numOfMotor;

	@Column(name = "motorPower")
	private String motorPower;

	@Column(name = "maxSpeed")
	private int maxSpeed;

	@Column(name = "navigationEquipment")
	private String navigationEquipment;

	@Column(name = "capacity")
	private String capacity;

	@Column(name = "fishingEquipment")
	private String fishingEquipment;

	@Column(name = "price")
	private String price;

	public Boat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boat(Long id, boolean isDeleted, String name, String address, String description, List<String> photos,
			String rules, int numOfPeople, int cancelationConditions, double averageRating) {
		super(id, isDeleted, name, address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
		// TODO Auto-generated constructor stub
	}

	public Boat(String type, String length, String numOfMotor, String motorPower, int maxSpeed,
			String navigationEquipment, String capacity, String fishingEquipment, String price) {
		super();
		this.type = type;
		this.length = length;
		this.numOfMotor = numOfMotor;
		this.motorPower = motorPower;
		this.maxSpeed = maxSpeed;
		this.navigationEquipment = navigationEquipment;
		this.capacity = capacity;
		this.fishingEquipment = fishingEquipment;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getNumOfMotor() {
		return numOfMotor;
	}

	public void setNumOfMotor(String numOfMotor) {
		this.numOfMotor = numOfMotor;
	}

	public String getMotorPower() {
		return motorPower;
	}

	public void setMotorPower(String motorPower) {
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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}

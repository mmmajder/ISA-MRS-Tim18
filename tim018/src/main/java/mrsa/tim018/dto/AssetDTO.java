package mrsa.tim018.dto;

import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Resort;

public class AssetDTO {
	private Long id;
	private boolean isDeleted;
	private Renter renter;
	private String name;
	private String address;
	private String description;
	private String rules;
	private int numOfPeople;
	private int cancelationConditions; // (0-100)
	private double averageRating;
	private AssetType assetType;
	private double price;
	private long renterId;
	// resort
	private int numberOfBeds;
	private int numberOfRooms;

	// boat
	private String boatType;
	private int length;
	private int numOfMotor;
	private int motorPower;
	private int maxSpeed;
	private String navigationEquipment;
	private String fishingEquipment;

	public AssetDTO() {
	}

	public AssetDTO(Asset asset) {
		this(asset.getID(), asset.getAssetType(), asset.isDeleted(), asset.getRenter(), asset.getName(),
				asset.getAddress(), asset.getDescription(), asset.getRules(), asset.getNumOfPeople(),
				asset.getCancelationConditions(), asset.getAverageRating(), asset.getPrice());
	}

	public AssetDTO(Resort resort) {
		this(resort.getID(), resort.getAssetType(), resort.isDeleted(), resort.getRenter(), resort.getName(),
				resort.getAddress(), resort.getDescription(), resort.getRules(), resort.getNumOfPeople(),
				resort.getCancelationConditions(), resort.getAverageRating(), resort.getPrice());
		this.numberOfBeds = resort.getNumberOfBeds();
		this.numberOfRooms = resort.getNumberOfRooms();
	}

	public AssetDTO(Boat boat) {
		this(boat.getID(), boat.getAssetType(), boat.isDeleted(), boat.getRenter(), boat.getName(), boat.getAddress(),
				boat.getDescription(), boat.getRules(), boat.getNumOfPeople(), boat.getCancelationConditions(),
				boat.getAverageRating(), boat.getPrice());
		this.boatType = boat.getBoatType();
		this.length = boat.getLength();
		this.numOfMotor = boat.getNumOfMotor();
		this.motorPower = boat.getMotorPower();
		this.maxSpeed = boat.getMaxSpeed();
		this.navigationEquipment = boat.getNavigationEquipment();
		this.fishingEquipment = boat.getFishingEquipment();
	}

	public AssetDTO(Adventure a) {
		this(a.getID(), a.getAssetType(), a.isDeleted(), a.getRenter(), a.getName(), a.getAddress(), a.getDescription(),
				a.getRules(), a.getNumOfPeople(), a.getCancelationConditions(), a.getAverageRating(), a.getPrice());
		this.fishingEquipment = a.getFishingEquipment();
	}

	public AssetDTO(Long id, AssetType assetType, boolean isDeleted, Renter renter, String name, String address,
			String description, String rules, int numOfPeople, int cancelationConditions, double averageRating,
			double price) {
		this.id = id;
		this.assetType = assetType;
		this.isDeleted = isDeleted;
		this.renter = renter;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rules = rules;
		this.numOfPeople = numOfPeople;
		this.cancelationConditions = cancelationConditions; // (0-100)
		this.averageRating = averageRating;
		this.price = price;
	}



	public long getRenterId() {
		return renterId;
	}

	public Long getId() {
		return id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public Renter getRenter() {
		return renter;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public String getRules() {
		return rules;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public int getCancelationConditions() {
		return cancelationConditions;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public double getPrice() {
		return price;
	}

	// resort
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	// boat
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setRenter(Renter renter) {
		this.renter = renter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public void setCancelationConditions(int cancelationConditions) {
		this.cancelationConditions = cancelationConditions;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRenterId(long renterId) {
		this.renterId = renterId;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setNumOfMotor(int numOfMotor) {
		this.numOfMotor = numOfMotor;
	}

	public void setMotorPower(int motorPower) {
		this.motorPower = motorPower;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setNavigationEquipment(String navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}
	

}

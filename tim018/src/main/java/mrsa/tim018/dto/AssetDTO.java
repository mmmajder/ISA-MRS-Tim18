package mrsa.tim018.dto;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.model.Renter;

public class AssetDTO {
	private Long id;
	private boolean isDeleted;
	private Renter renter;
	private String name;
	private String address;
	private String description;
	private String rules;
	private int numOfPeople;
	private int cancelationConditions; //(0-100)
	private double averageRating;
	private FishingInstructor fishingInstructor;
	
	public AssetDTO() {}
	
	public AssetDTO(Asset asset) {
		this(asset.getID(), asset.isDeleted(), asset.getRenter(), asset.getName(),
				asset.getAddress(), asset.getDescription(), asset.getRules(), asset.getNumOfPeople(),
				asset.getCancelationConditions(), asset.getAverageRating(), asset.getFishingInstructor());
	}
	
	public AssetDTO(Long id, boolean isDeleted, 
			Renter renter, String name, String address,
			String description, String rules, int numOfPeople,
			int cancelationConditions, double averageRating,
			FishingInstructor fishingInstructor) {
		this.id = id;
		this.isDeleted = isDeleted;
		this.renter = renter;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rules = rules;
		this.numOfPeople = numOfPeople;
		this.cancelationConditions = cancelationConditions; //(0-100)
		this.averageRating = averageRating;
		this.fishingInstructor = fishingInstructor;
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

	public FishingInstructor getFishingInstructor() {
		return fishingInstructor;
	}
}

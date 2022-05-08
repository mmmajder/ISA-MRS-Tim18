package mrsa.tim018.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Adventure extends Asset {
	@Column(name = "fishingEquipment")
	private String fishingEquipment;
	
	public Adventure() {
		super();
	}
	
	public Adventure(Long id, boolean isDeleted, AssetType assetType, String name, String address, String description, List<String> photos,
			String rules, int numOfPeople, int cancelationConditions, double averageRating, String boatType, int length, int numOfMotor, int motorPower, int maxSpeed,
			String navigationEquipment, String fishingEquipment) {
		super(id, isDeleted, assetType, name, address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
		this.fishingEquipment = fishingEquipment;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}
	
	
}

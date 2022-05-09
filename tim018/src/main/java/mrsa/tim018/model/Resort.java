package mrsa.tim018.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Resort extends Asset {

	@Column(name = "numberOfBeds")
	private int numberOfBeds;

	@Column(name = "numberOfRooms")
	private int numberOfRooms;

	public Resort() {
		super();
	}

	public Resort(Long id, boolean isDeleted, AssetType assetType, String name, String address, String description,
			List<String> photos, String rules, int numOfPeople, int cancelationConditions, double averageRating) {
		super(id, isDeleted, assetType, name, address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
		// TODO Auto-generated constructor stub
	}

	public Resort(Long id, boolean isDeleted, AssetType assetType, String name, String address, String description,
			List<String> photos, String rules, int numOfPeople, int cancelationConditions, double averageRating,
			int numberOfRooms, int numberOfBeds) {
		super(id, isDeleted, assetType, name, address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
		this.numberOfRooms = numberOfRooms;
		this.numberOfBeds = numberOfBeds;
	}

	public Resort(boolean isDeleted, Renter renter, String name, AssetType assetType, String address,
			String description, List<Photo> photos, String rules, int numOfPeople, int cancelationConditions,
			double averageRating, double price, AssetCalendar calendar, List<PriceCatalog> prices, int numberOfBeds,
			int numberOfRooms) {
		super(isDeleted, renter, name, assetType, address, description, photos, rules, numOfPeople,
				cancelationConditions, averageRating, price, calendar, prices);
		this.numberOfBeds = numberOfBeds;
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
}

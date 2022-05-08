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

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Room> rooms;

	public Resort() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resort(Long id, boolean isDeleted, String name, String address, String description, List<String> photos,
			String rules, int numOfPeople, int cancelationConditions, double averageRating) {
		super(id, isDeleted, name, address, description, photos, rules, numOfPeople, cancelationConditions,
				averageRating);
		// TODO Auto-generated constructor stub
	}

	public Resort(List<Room> rooms) {
		super();
		this.rooms = rooms;
	}

}

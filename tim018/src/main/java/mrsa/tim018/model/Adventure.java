package mrsa.tim018.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Adventure extends Asset {
	@OneToMany
	private List<FishingEquipment> fishingEquipment;
	
}

package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class FishingInstructor extends User {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Asset> assets = new ArrayList<Asset>();
	
	public FishingInstructor() {
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
	
	

	
	
}

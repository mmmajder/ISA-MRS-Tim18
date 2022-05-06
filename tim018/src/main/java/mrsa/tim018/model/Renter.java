package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Renter extends User {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "renter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Asset> assets = new ArrayList<Asset>();
	
	public Renter() {
	}

	public Renter(List<Asset> assets) {
		super();
		this.assets = assets;
	}

	// DTO
	public Renter(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, String email, String password, boolean enabled, List<Asset> assets) {
		super(iD, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, email, password, enabled);
		this.assets = assets;
	}

	
	public Renter(Long iD, String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, String email, String password,  List<Asset> assets) {
		super(iD, firstName, lastName, address, city, state, phoneNum, userType, email, password);
		this.assets = assets;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
}

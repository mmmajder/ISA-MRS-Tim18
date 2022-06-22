package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(value= {"assets"})
public class Renter extends User {
	
	private static final long serialVersionUID = 1L;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "renter", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Asset> assets = new ArrayList<>();
	
	public Renter() {
	}

	public Renter(List<Asset> assets) {
		super();
		this.assets = assets;
	}

	// DTO
	public Renter(Long iD, boolean isDeleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, UserType userType, int loyaltyPoints, String email, String password, boolean enabled, String verificationCode, String profilePhotoId, List<Asset> assets) {
		super(iD, isDeleted, firstName, lastName, address, city, state, phoneNum, userType, loyaltyPoints, email, password, enabled, verificationCode, profilePhotoId);
		this.assets = assets;
	}

	
	public Renter(User user) {
		super(user);
	}
	
	public Renter(Long iD, String firstName, String lastName, String address, String city, String state,
			String phoneNum, UserType userType, String email, String password,  List<Asset> assets, String profilePhotoId) {
		super(iD, firstName, lastName, address, city, state, phoneNum, userType, email, password, profilePhotoId);
		this.assets = assets;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
}

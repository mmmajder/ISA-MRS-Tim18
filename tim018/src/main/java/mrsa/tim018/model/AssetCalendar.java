package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
public class AssetCalendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<TimeRange> available = new ArrayList<>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<SpecialOffer> specialPrice = new ArrayList<>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Reservation> reserved = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public AssetCalendar() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TimeRange> getAvailable() {
		return available;
	}

	public void setAvailable(List<TimeRange> available) {
		this.available = available;
	}

	public List<SpecialOffer> getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(List<SpecialOffer> specialPrice) {
		this.specialPrice = specialPrice;
	}

	public List<Reservation> getReserved() {
		return reserved;
	}

	public void setReserved(List<Reservation> reserved) {
		this.reserved = reserved;
	}

}

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

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AssetCalendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TimeRange> availablePattern = new ArrayList<TimeRange>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TimeRange> availableSingle = new ArrayList<TimeRange>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TimeRange> specialPricePattern = new ArrayList<TimeRange>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<SpecialOffer> specialPriceSingle = new ArrayList<SpecialOffer>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Reservation> reserved = new ArrayList<Reservation>();

	public Long getId() {
		return id;
	}

	public AssetCalendar() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TimeRange> getAvailablePattern() {
		return availablePattern;
	}

	public void setAvailablePattern(List<TimeRange> availablePattern) {
		this.availablePattern = availablePattern;
	}

	public List<TimeRange> getAvailableSingle() {
		return availableSingle;
	}

	public void setAvailableSingle(List<TimeRange> availableSingle) {
		this.availableSingle = availableSingle;
	}

	public List<TimeRange> getSpecialPricePattern() {
		return specialPricePattern;
	}

	public void setSpecialPricePattern(List<TimeRange> specialPricePattern) {
		this.specialPricePattern = specialPricePattern;
	}

	public List<SpecialOffer> getSpecialPriceSingle() {
		return specialPriceSingle;
	}

	public void setSpecialPriceSingle(List<SpecialOffer> specialPriceSingle) {
		this.specialPriceSingle = specialPriceSingle;
	}

	public List<Reservation> getReserved() {
		return reserved;
	}

	public void setReserved(List<Reservation> reserved) {
		this.reserved = reserved;
	}

}

package mrsa.tim018.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TimeRange> availablePattern = new ArrayList<TimeRange>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TimeRange> availableSingle = new ArrayList<TimeRange>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TimeRange> specialPricePattern = new ArrayList<TimeRange>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SpecialOffer> specialPriceSingle = new ArrayList<SpecialOffer>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reserved = new ArrayList<Reservation>();
}

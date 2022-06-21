package mrsa.tim018.dto.calendar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.SpecialOffer;
import mrsa.tim018.model.TimeRange;

public class AssetCalendarDTO {

	public List<TimeRange> available;
	public List<SpecialOffer> specialPrice;
	public List<Reservation> reserved;
	
	public AssetCalendarDTO(List<TimeRange> available, List<SpecialOffer> specialPrice, List<Reservation> reserved) {
		this.available = available;
		this.specialPrice = specialPrice;
		this.reserved = reserved;
	}

}

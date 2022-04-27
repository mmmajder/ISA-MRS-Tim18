package mrsa.tim018.dto.calendar;

import mrsa.tim018.model.AssetCalendar;

public class UserCalendarsDTO {
	private Long id;
	private AssetCalendar calendar;

	public UserCalendarsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCalendarsDTO(Long id, AssetCalendar calendar) {
		super();
		this.id = id;
		this.calendar = calendar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AssetCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(AssetCalendar calendar) {
		this.calendar = calendar;
	}

}

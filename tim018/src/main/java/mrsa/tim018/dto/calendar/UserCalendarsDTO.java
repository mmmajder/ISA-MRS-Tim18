package mrsa.tim018.dto.calendar;

import mrsa.tim018.model.AssetCalendar;

public class UserCalendarsDTO {
	private long id;
	private String name;
	private AssetCalendar calendar;

	public UserCalendarsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCalendarsDTO(long id, String name, AssetCalendar calendar) {
		super();
		this.id = id;
		this.name = name;
		this.calendar = calendar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssetCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(AssetCalendar calendar) {
		this.calendar = calendar;
	}

}

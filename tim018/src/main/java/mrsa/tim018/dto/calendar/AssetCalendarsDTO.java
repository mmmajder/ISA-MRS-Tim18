package mrsa.tim018.dto.calendar;

import mrsa.tim018.model.AssetCalendar;

public class AssetCalendarsDTO {
	private long id;
	private String name;
	private AssetCalendar calendar;
	
	public AssetCalendarsDTO() {
		super();
	}

	public AssetCalendarsDTO(long id, String name, AssetCalendar calendar) {
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

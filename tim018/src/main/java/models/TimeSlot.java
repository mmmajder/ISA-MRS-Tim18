package models;

import java.util.Objects;

public class TimeSlot {

	private String ID;
	private int time;
	
	public TimeSlot() {
	}

	public TimeSlot(String iD, int time) {
		super();
		ID = iD;
		this.time = time;
	}

	public int getTime() {
		return time;
	}
	public String getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "TimeSlot [ID=" + ID + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		return Objects.equals(ID, other.ID) && time == other.time;
	}
	
	

}

package mrsa.tim018.dto.calendar;

import java.util.List;

import mrsa.tim018.model.TimeRange;

public class AssetPeriodsDTO {
	private Long id;
	private List<TimeRange> periods;

	public AssetPeriodsDTO(Long id, List<TimeRange> periods) {
		super();
		this.id = id;
		this.periods = periods;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TimeRange> getPeriods() {
		return periods;
	}

	public void setPeriods(List<TimeRange> periods) {
		this.periods = periods;
	}

}

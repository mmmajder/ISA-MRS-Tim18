package mrsa.tim018.dto.calendar;

import mrsa.tim018.model.TimeRange;

public class TimeRangeMergeElement {
	private TimeRange timeRange;
	private boolean isReduced;

	public TimeRangeMergeElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeRangeMergeElement(TimeRange timeRange, boolean isReduced) {
		super();
		this.timeRange = timeRange;
		this.isReduced = isReduced;
	}

	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}

	public boolean isReduced() {
		return isReduced;
	}

	public void setReduced(boolean isReduced) {
		this.isReduced = isReduced;
	}

}

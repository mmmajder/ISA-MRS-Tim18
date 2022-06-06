package mrsa.tim018.model;

import java.time.LocalDate;

public class ReportFilters {

	private boolean completed;
	private boolean canceled;
	private boolean potential;
	private LocalDate fromDate;
	private LocalDate toDate;
	
	public ReportFilters() {};
	
	public ReportFilters(boolean completed, boolean canceled, boolean potential, LocalDate fromDate, LocalDate toDate) {
		this.completed = completed;
		this.canceled = canceled;
		this.potential = potential;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean isPotential() {
		return potential;
	}

	public void setPotential(boolean potential) {
		this.potential = potential;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	};
}

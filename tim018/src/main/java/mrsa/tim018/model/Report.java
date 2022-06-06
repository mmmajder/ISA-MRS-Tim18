package mrsa.tim018.model;

import java.util.Date;

public class Report {

	private Date timestamp;
	private Double income;
	private Long numberOfReservations;
	private String group;
	
	public Report() {};
	
	public Report(String group) {
		this.group = group;
	};
	
	public Report(Date timestamp, Double income, Long numberOfReservations) {
		this.timestamp = timestamp;
		this.income = income;
		this.numberOfReservations = numberOfReservations;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Long getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(Long numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	public void incIncome(double additionalIncome) {
		this.income += additionalIncome;
	}
	
	public void incNumberOfReservations(Long additionalReservations) {
		this.numberOfReservations += additionalReservations;
	}
}

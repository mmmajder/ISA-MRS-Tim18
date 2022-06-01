package mrsa.tim018.model;

public class Report {

	private String group;
	private Double income;
	private Integer numberOfReservations;
	
	public Report() {};
	
	public Report(String group, Double income, Integer numberOfReservations) {
		this.group = group;
		this.income = income;
		this.numberOfReservations = numberOfReservations;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Integer getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(Integer numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}
}

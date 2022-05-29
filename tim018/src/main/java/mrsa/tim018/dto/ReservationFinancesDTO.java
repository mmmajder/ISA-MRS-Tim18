package mrsa.tim018.dto;

import mrsa.tim018.model.ReservationFinances;

public class ReservationFinancesDTO {
	private int pointsPerReservation;
	private int reservationTax;

	public ReservationFinancesDTO(int pointsPerReservation, int reservationTax) {
		super();
		this.pointsPerReservation = pointsPerReservation;
		this.reservationTax = reservationTax;
	}

	public ReservationFinancesDTO(ReservationFinances reservationFinances) {
		this.pointsPerReservation = reservationFinances.getPointsPerReservation();
		this.reservationTax = reservationFinances.getReservationTax();
	}
	
	public ReservationFinancesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPointsPerReservation() {
		return pointsPerReservation;
	}

	public void setPointsPerReservation(int pointsPerReservation) {
		this.pointsPerReservation = pointsPerReservation;
	}

	public int getReservationTax() {
		return reservationTax;
	}

	public void setReservationTax(int reservationTax) {
		this.reservationTax = reservationTax;
	}

}

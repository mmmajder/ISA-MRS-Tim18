package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReservationFinances {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "pointsPerReservation")
	private int pointsPerReservation;

	@Column(name = "reservationTax")
	private int reservationTax;

	public ReservationFinances(Long iD, int pointsPerReservation, int reservationTax) {
		super();
		ID = iD;
		this.pointsPerReservation = pointsPerReservation;
		this.reservationTax = reservationTax;
	}

	public ReservationFinances() {
		super();
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
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

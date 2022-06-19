package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoyaltyState {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "taxPercent", nullable = false)
	private double taxPercent;
	@Column(name = "clientDiscountPercent", nullable = false)
	private double clientDiscountPercent;
	@Column(name = "renterDiscountPercent", nullable = false)
	private double renterDiscountPercent;

	public LoyaltyState() {
		super();
	}

	public LoyaltyState(Long id, double taxPercent, double clientDiscountPercent, double renterDiscountPercent) {
		super();
		this.id = id;
		this.taxPercent = taxPercent;
		this.clientDiscountPercent = clientDiscountPercent;
		this.renterDiscountPercent = renterDiscountPercent;
	}

	public LoyaltyState(double taxPercent, double clientDiscountPercent, double renterDiscountPercent) {
		super();
		this.taxPercent = taxPercent;
		this.clientDiscountPercent = clientDiscountPercent;
		this.renterDiscountPercent = renterDiscountPercent;
	}

	public double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public double getClientDiscountPercent() {
		return clientDiscountPercent;
	}

	public void setClientDiscountPercent(double clientDiscountPercent) {
		this.clientDiscountPercent = clientDiscountPercent;
	}

	public double getRenterDiscountPercent() {
		return renterDiscountPercent;
	}

	public void setRenterDiscountPercent(double renterDiscountPercent) {
		this.renterDiscountPercent = renterDiscountPercent;
	}

}

package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoyaltyProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "level", nullable = false)
	private String level;
	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	@Column(name = "points", nullable = false)
	private int points;
	@Column(name = "discount", nullable = false)
	private double discount;
	@Column(name = "userDiscountType", nullable = false)
	private UserDiscountType userDiscountType;

	public LoyaltyProgram() {
	}

	public LoyaltyProgram(Long id, String level, boolean isDeleted, int points, double discount,
			UserDiscountType userDiscountType) {
		super();
		this.id = id;
		this.level = level;
		this.isDeleted = isDeleted;
		this.points = points;
		this.discount = discount;
		this.userDiscountType = userDiscountType;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDiscountType getUserDiscountType() {
		return userDiscountType;
	}

	public void setUserDiscountType(UserDiscountType userDiscountType) {
		this.userDiscountType = userDiscountType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}

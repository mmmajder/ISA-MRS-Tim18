package mrsa.tim018.dto;

import mrsa.tim018.model.LoyaltyProgram;
import mrsa.tim018.model.UserDiscountType;

public class LoyaltyElementDTO {
	private String level;
	private int points;
	private double discount;
	private UserDiscountType userDiscountType;

	public LoyaltyElementDTO() {
		super();
	}

	public LoyaltyElementDTO(LoyaltyProgram loyaltyProgram) {
		level = loyaltyProgram.getLevel();
		points = loyaltyProgram.getPoints();
		discount = loyaltyProgram.getDiscount();
		userDiscountType = loyaltyProgram.getUserDiscountType();
	}

	public LoyaltyElementDTO(String level, int points, double discount,
			mrsa.tim018.model.UserDiscountType userDiscountType) {
		super();
		this.level = level;
		this.points = points;
		this.discount = discount;
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

	public UserDiscountType getUserDiscountType() {
		return userDiscountType;
	}

	public void setUserDiscountType(UserDiscountType userDiscountType) {
		this.userDiscountType = userDiscountType;
	}

}

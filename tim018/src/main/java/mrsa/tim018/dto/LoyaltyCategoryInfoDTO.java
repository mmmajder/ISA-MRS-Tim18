package mrsa.tim018.dto;

public class LoyaltyCategoryInfoDTO {

	private String category;
	private int pointsToUpgrade;
	
	public LoyaltyCategoryInfoDTO(String category, int pointsToUpgrade) {
		super();
		this.category = category;
		this.pointsToUpgrade = pointsToUpgrade;
	}
	public LoyaltyCategoryInfoDTO() {
		super();
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPointsToUpgrade() {
		return pointsToUpgrade;
	}
	public void setPointsToUpgrade(int pointsToUpgrade) {
		this.pointsToUpgrade = pointsToUpgrade;
	}
	
	
}

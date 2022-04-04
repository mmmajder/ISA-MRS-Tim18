package models;

import java.util.Date;

public class PriceCatalog {

	private Long ID;
	private boolean isDeleted;
	
	private double price;
	
	private Date startDate;
	private Date endDate = null;
	
	public PriceCatalog() {
		// TODO Auto-generated constructor stub
	}

	public PriceCatalog(Long iD, boolean isDeleted, double price, Date startDate, Date endDate) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getID() {
		return ID;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	

}

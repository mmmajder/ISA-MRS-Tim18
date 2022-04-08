package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriceCatalog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "isDeleted", unique = true, nullable = false)
	private boolean isDeleted;


	@Column(name = "price", unique = true, nullable = false)
	private double price;
	
	@Column(name = "startDate", unique = true, nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", unique = true, nullable = false)
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

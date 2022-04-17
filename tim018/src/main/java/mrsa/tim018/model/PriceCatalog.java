package mrsa.tim018.model;

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
	private Long id;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate = null;
	
	public PriceCatalog() {
		// TODO Auto-generated constructor stub
	}

	public PriceCatalog(Long iD, boolean isDeleted, double price, Date startDate, Date endDate) {
		super();
		id = iD;
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
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}
}

package mrsa.tim018.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TimeRange {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;
	@Column(name = "fromDateTime", nullable = false)
	private LocalDateTime fromDateTime;
	@Column(name = "toDateTime", nullable = false)
	private LocalDateTime toDateTime;

	public TimeRange(boolean isDeleted, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		super();
		this.isDeleted = isDeleted;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
	}

	public TimeRange() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

}

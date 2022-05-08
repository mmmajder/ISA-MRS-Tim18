package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FishingEquipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "number", nullable = false)
	private int number;
	

}

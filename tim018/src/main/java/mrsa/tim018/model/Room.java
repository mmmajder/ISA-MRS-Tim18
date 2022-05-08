package mrsa.tim018.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numberOfRooms", nullable = false)
	private int roomNumber;

	@Column(name = "numberOfBeds", nullable = false)
	private int numberOfBeds;

	public Room(Long id, int roomNumber, int numberOfBeds) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.numberOfBeds = numberOfBeds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

}

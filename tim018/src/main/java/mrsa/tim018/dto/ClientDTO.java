package mrsa.tim018.dto;

import mrsa.tim018.model.Client;

public class ClientDTO {
	
	private Long id;
	private boolean isDeleted;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phoneNum;
	private int loyaltyPoints;
	private String email;
	private String password;
	private int penaltyPoints;

	public ClientDTO() {
	}

	public ClientDTO(Client client) {
		this(client.getID(), client.isDeleted(), client.getFirstName(), client.getLastName(), 
		     client.getAddress(), client.getCity(), client.getState(), client.getPhoneNum(), client.getLoyaltyPoints(),
		     client.getEmail(), client.getPassword(), client.getPenaltyPoints());
		
	}
	public ClientDTO(Long id, boolean deleted, String firstName, String lastName, String address, String city,
			String state, String phoneNum, int loyaltyPoints, String email,  String password, int penaltyPoints) {
		
		this.id = id;
		this.isDeleted = deleted;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.loyaltyPoints = loyaltyPoints;
		this.email = email;
		this.password = password;
		this.penaltyPoints = penaltyPoints;
	}

	public Long getId() {
		return id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	

}

package models;

import java.util.Objects;

public class UserAccount {

	private Long ID;
	private boolean isDeleted;
	
	private String email;
	private String password;
		
	public UserAccount() {
	}

	
	
	public UserAccount(Long iD, boolean isDeleted, String email, String password) {
		super();
		ID = iD;
		this.isDeleted = isDeleted;
		this.email = email;
		this.password = password;
	}
	
	public UserAccount(Long iD, String email, String password) {
		super();
		ID = iD;
		this.email = email;
		this.password = password;
		this.isDeleted = false;
	}



	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getID() {
		return ID;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, email, isDeleted, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(email, other.email) && isDeleted == other.isDeleted
				&& Objects.equals(password, other.password);
	}

	

}

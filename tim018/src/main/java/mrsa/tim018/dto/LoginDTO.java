package mrsa.tim018.dto;

import mrsa.tim018.model.UserType;

public class LoginDTO {

	public UserTokenState token;
	public UserType userType;
	
	public LoginDTO() {
		
	}
	
	public LoginDTO(UserTokenState token, UserType userType) {
		this.token = token;
		this.userType = userType;
	}
}
	
package mrsa.tim018.mapper;

import mrsa.tim018.dto.UserRequest;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.utils.StringUtils;

public class UserMapper {
	public static User mapRequestToUser(UserRequest userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(StringUtils.capitalizeAllWords(userDTO.getFirstName()));
		user.setLastName(StringUtils.capitalizeAllWords(userDTO.getLastName()));
		user.setAddress(StringUtils.capitalizeFirstWord(userDTO.getAddress()));
		user.setCity(StringUtils.capitalizeFirstWord(userDTO.getCity()));
		user.setState(StringUtils.capitalizeFirstWord(userDTO.getState()));
		user.setPhoneNum(userDTO.getPhoneNum());
		user.setUserType(UserType.valueOf(userDTO.getUserType()));
		
		return user;
	}
	
	
}

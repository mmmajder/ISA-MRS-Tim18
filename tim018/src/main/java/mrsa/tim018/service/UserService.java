package mrsa.tim018.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.User;
import mrsa.tim018.repository.RenterRepository;
import mrsa.tim018.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User findOne(Long id) {
		User user = userRepository.findById(id).get();
		return user;
	}
}

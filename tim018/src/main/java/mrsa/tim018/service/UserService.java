package mrsa.tim018.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.User;
import mrsa.tim018.repository.FishingInstructorRepository;
import mrsa.tim018.repository.UserRepository;

@Service
public class UserService<T> implements UserDetailsService{
//	@Autowired
//	private ClientRepository clientRepository;
	
//	@Autowired
//	private RenterRepository renterRepository;
	
//	@Autowired
//	private AdminRepository adminRepository;
	
	@Autowired
	private FishingInstructorRepository fishingInstructorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;*/

	
	private User userExist(Long id, JpaRepository<T, Long> repo) {
		return (User) repo.findById(id).get();
			
	}
	
	@SuppressWarnings("unchecked")
	public User findOne(Long id) {
		ArrayList<JpaRepository<T, Long>> repositories = new ArrayList<JpaRepository<T,Long>>();
		repositories.add((JpaRepository<T, Long>) fishingInstructorRepository);
		for (JpaRepository<T, Long> repository : repositories) {
			User user = userExist(id, repository);
			if (user!=null) { return user; }
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}
	
	public User findByEmail(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}

	public User save(User user) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}

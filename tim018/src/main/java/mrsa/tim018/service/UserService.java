package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import mrsa.tim018.model.Client;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.UserType;
import mrsa.tim018.model.Role;
import mrsa.tim018.repository.ClientRepository;
import mrsa.tim018.repository.RenterRepository;
import mrsa.tim018.repository.RoleRepository;
import mrsa.tim018.repository.UserRepository;

@Service
public class UserService<T> implements UserDetailsService{
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RenterRepository renterRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@SuppressWarnings("unchecked")
	public User findOne(Long id) {
		return userRepository.findById(id).get();
	}
	
	public Client findClient(Long id) {
		return clientRepository.findById(id).get();
	}
	
	public Client saveClient(Client client) {
		return clientRepository.save(client);
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
		user.setId(userRepository.getNextSeriesId());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return updateUser(user);
		
	}
	
	public User saveChanges(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		UserType userType = user.getUserType();
		if(userType == UserType.Client) {
			User bClass = new Client(user);
			Client childClass = (Client) bClass;
			return clientRepository.save(childClass);
		}
		if(userType == UserType.ResortRenter || userType == UserType.BoatRenter || userType == UserType.FishingInstructor) {
			User bClass = new Renter(user);
			Renter childClass = (Renter) bClass;
			return renterRepository.save(childClass);
		}
		return null;
	}
	
	public User verify(String verificationCode) {
		User user = userRepository.findByVerificationCode(verificationCode);
		
		if (user == null || user.isEnabled()) {
			return null;
		}
		
		user.setVerificationCode(null);
		user.setEnabled(true);
		user = userRepository.save(user);
		return user;
	}
	
	
	public List<Role> findAllRoles(){
		return roleRepository.findAll();
	}
	
	public Role findRolesByUserType(String name){
		return roleRepository.findByName(name);
	}
	

}

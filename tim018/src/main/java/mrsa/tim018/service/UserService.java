package mrsa.tim018.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.FishingInstructor;
import mrsa.tim018.model.User;
import mrsa.tim018.repository.FishingInstructorRepository;
import mrsa.tim018.repository.UserRepository;

@Service
public class UserService<T> {
//	@Autowired
//	private ClientRepository clientRepository;
	
//	@Autowired
//	private RenterRepository renterRepository;
	
//	@Autowired
//	private AdminRepository adminRepository;
	
	@Autowired
	private FishingInstructorRepository fishingInstructorRepository;
	
	private User userExist(Long id, JpaRepository<T, Long> repo) {
		return (User) repo.findById(id).get();
			
	}
	
	public User findOne(Long id) {
		ArrayList<JpaRepository<T, Long>> repositories = new ArrayList<JpaRepository<T,Long>>();
		repositories.add((JpaRepository<T, Long>) fishingInstructorRepository);
		for (JpaRepository<T, Long> repository : repositories) {
			User user = userExist(id, repository);
			if (user!=null) { return user; }
		}
		return null;
	}
}

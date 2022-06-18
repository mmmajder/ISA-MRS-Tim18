package mrsa.tim018.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Admin;
import mrsa.tim018.model.User;
import mrsa.tim018.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminRepository adminRepo;
	
	public Admin findOne(Long id) {
		return adminRepo.findById(id).orElse(null);
	}

	public User save(User user) {
		return adminRepo.save(user);
	}
	
	public Admin save(Admin admin) {
		return adminRepo.save(admin);
	}

}

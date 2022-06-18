package mrsa.tim018.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Admin;
import mrsa.tim018.model.User;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	public Page<Admin> findAll(Pageable pageable);
	
	public Admin findById(long id);

	public Admin save(Admin admin);
	
	public User save(User user);
}

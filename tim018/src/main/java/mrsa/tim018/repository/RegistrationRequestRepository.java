package mrsa.tim018.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Registration;

public interface RegistrationRequestRepository extends JpaRepository<Registration, Long> {
	public List<Registration> findAll();
	public Registration findById(long id);

}
 
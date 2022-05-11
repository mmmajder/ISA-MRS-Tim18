package mrsa.tim018.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mrsa.tim018.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findById(Long id);
	
	public User findByEmail(String email);
	
	@Query(value = "SELECT nextval('userSeqGen')", nativeQuery = true)
	Long getNextSeriesId();
}

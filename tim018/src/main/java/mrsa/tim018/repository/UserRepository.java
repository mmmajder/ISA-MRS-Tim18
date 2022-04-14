package mrsa.tim018.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findById(Long id);
}

package mrsa.tim018.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.LoyaltyState;

public interface LoyaltyStateRepository extends JpaRepository<LoyaltyState, Long> {
	public LoyaltyState findById(long id);
	public List<LoyaltyState> findAll();

}

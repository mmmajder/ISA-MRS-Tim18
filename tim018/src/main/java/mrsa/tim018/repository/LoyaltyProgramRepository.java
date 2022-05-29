package mrsa.tim018.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.LoyaltyProgram;
import mrsa.tim018.model.UserDiscountType;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
	public LoyaltyProgram findById(long id);
	public List<LoyaltyProgram> findAll();
	public List<LoyaltyProgram> findByLevelAndUserDiscountType(String level, UserDiscountType userDiscountType);
}

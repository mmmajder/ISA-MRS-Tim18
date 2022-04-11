package mrsa.tim018.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.FishingInstructor;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {
	public FishingInstructor findOneByIndex(String index);
	
	public List<FishingInstructor> findAllFishingInstructors();
}

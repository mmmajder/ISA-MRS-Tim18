package mrsa.tim018.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mrsa.tim018.model.FishingInstructor;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {
	public Page<FishingInstructor> findAll(Pageable pageable);
	
	public List<FishingInstructor> findByFirstNameAndLastNameAllIgnoringCase(String firstName, String lastName);

	public FishingInstructor findById(long id);
	
	public FishingInstructor deleteById(int id);
	
	@Query(value = "SELECT nextval('userSeqGen')", nativeQuery = true)
	Long getNextSeriesId();
}

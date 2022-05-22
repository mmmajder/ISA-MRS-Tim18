package mrsa.tim018.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mrsa.tim018.model.Renter;
import mrsa.tim018.model.User;

public interface RenterRepository extends JpaRepository<Renter, Long> {
	public Page<Renter> findAll(Pageable pageable);
	
	public List<Renter> findByFirstNameAndLastNameAllIgnoringCase(String firstName, String lastName);

	public Renter findById(long id);
	
	public Renter deleteById(int id);
	
	@Query(value = "SELECT nextval('userSeqGen')", nativeQuery = true)
	Long getNextSeriesId();

	public User save(User renter);
}

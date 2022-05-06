package mrsa.tim018.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Renter;

public interface RenterRepository extends JpaRepository<Renter, Long> {
	public Page<Renter> findAll(Pageable pageable);
	
	public List<Renter> findByFirstNameAndLastNameAllIgnoringCase(String firstName, String lastName);

	public Renter findById(long id);
	
	public Renter deleteById(int id);
	
	
}

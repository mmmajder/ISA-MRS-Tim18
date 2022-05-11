package mrsa.tim018.repository;

import mrsa.tim018.model.Client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

	public Page<Client> findAll(Pageable pageable);
	
	public List<Client> findByFirstNameAndLastNameAllIgnoringCase(String firstName, String lastName);
	
	@Query(value = "SELECT nextval('userSeqGen')", nativeQuery = true)
	Long getNextSeriesId();
}

package mrsa.tim018.repository;
import mrsa.tim018.model.Boat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoatRepository extends JpaRepository<Boat, Long> {
	public Page<Boat> findAll(Pageable pageable);
	
	public List<Boat> findAllByRenterId(long id);
	
	public Boat findById(long id);
}

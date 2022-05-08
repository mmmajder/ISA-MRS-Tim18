package mrsa.tim018.repository;

import mrsa.tim018.model.Resort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResortRepository extends JpaRepository<Resort, Long> {
	public Page<Resort> findAll(Pageable pageable);
	
	public List<Resort> findAllByRenterId(long id);
	
	public Resort findById(long id);
}

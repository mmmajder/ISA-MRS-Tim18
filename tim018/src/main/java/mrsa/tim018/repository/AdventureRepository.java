package mrsa.tim018.repository;

import mrsa.tim018.model.Adventure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
	public Page<Adventure> findAll(Pageable pageable);
	
	public List<Adventure> findAllByRenterId(long id);
	
	public Adventure findById(long id);
}


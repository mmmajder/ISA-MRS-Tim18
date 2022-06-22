package mrsa.tim018.repository;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Resort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface ResortRepository extends JpaRepository<Resort, Long> {
	public Page<Resort> findAll(Pageable pageable);
	
	public List<Resort> findAllByRenterId(long id);
	
	public Resort findById(long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Resort p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Resort findOneByIdLock(@Param("id")Long id);
}

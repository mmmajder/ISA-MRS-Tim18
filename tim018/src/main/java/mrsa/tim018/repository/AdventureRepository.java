package mrsa.tim018.repository;

import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.Asset;

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

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
	public Page<Adventure> findAll(Pageable pageable);
	
	public List<Adventure> findAllByRenterId(long id);
	
	public Adventure findById(long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Adventure p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Adventure findOneByIdLock(@Param("id")Long id);
}


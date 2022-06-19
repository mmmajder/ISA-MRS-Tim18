package mrsa.tim018.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import mrsa.tim018.model.SpecialOffer;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	public List<SpecialOffer> findAllByAssetId(long id);
	public Optional<SpecialOffer> findById(long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select s from SpecialOffer s where s.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Optional<SpecialOffer> findByIdAndLock(Long id);

}

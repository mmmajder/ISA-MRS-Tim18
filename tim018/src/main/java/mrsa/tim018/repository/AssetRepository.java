package mrsa.tim018.repository;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	public Page<Asset> findAll(Pageable pageable);
	
	public List<Asset> findAllByRenterId(long id);
	

	public Asset findById(long id);

/*	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("intersect select p from Asset p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Asset findOneById(@Param("id")Long id);
*/
	
	public List<Asset> findByAssetTypeAndIsDeleted(AssetType assetType, boolean isDeleted);

}
package mrsa.tim018.repository;

import mrsa.tim018.model.Asset;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	public Page<Asset> findAll(Pageable pageable);
	
	public List<Asset> findAllByRenterId(long id);
	
	public Asset findById(long id);

	@Query("SELECT u FROM Asset AS u LEFT JOIN FETCH u.subscriptions WHERE u.id=(:id)")
	public Asset joinFetchAssetWithSubsById(@Param("id") long id);
}
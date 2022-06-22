package mrsa.tim018.repository;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;

import java.util.ArrayList;
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
import org.springframework.data.jpa.repository.Query;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	public Page<Asset> findAll(Pageable pageable);
	
	public List<Asset> findAllByRenterId(long id);
	
	public Asset findById(long id);

	public List<Asset> findByAssetTypeAndIsDeleted(AssetType assetType, boolean isDeleted);

	@Query(value = "SELECT asset FROM Asset asset JOIN FETCH asset.calendar WHERE asset.id = :id")
	public Asset findsAssetsWithCalendar(long id);
}
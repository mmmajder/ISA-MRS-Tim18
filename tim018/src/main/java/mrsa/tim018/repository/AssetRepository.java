package mrsa.tim018.repository;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	public Page<Asset> findAll(Pageable pageable);
	
	public List<Asset> findAllByRenterId(long id);
	
	public Asset findById(long id);
	
	public List<Asset> findByAssetTypeAndIsDeleted(AssetType assetType, boolean isDeleted);
}
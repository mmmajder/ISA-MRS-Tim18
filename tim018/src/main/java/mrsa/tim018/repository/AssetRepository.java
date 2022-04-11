package mrsa.tim018.repository;

import mrsa.tim018.model.Asset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	public Page<Asset> findAll(Pageable pageable);
}
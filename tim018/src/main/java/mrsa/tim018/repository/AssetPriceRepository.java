package mrsa.tim018.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.AssetPrice;


public interface AssetPriceRepository  extends JpaRepository<AssetPrice, String> {

	@Query("SELECT price "
			+ "FROM AssetPrice price "
			+ "WHERE price.assetId = :assetId")
	Collection<AssetPrice> getAssetPrices(@Param("assetId") Long assetId);
}

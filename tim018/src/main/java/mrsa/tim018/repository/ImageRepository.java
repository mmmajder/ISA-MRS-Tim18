package mrsa.tim018.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.Image;

public interface ImageRepository  extends JpaRepository<Image, String> {

	@Query("SELECT i "
			+ "FROM Image i "
			+ "WHERE i.assetId = :assetId AND i.isDeleted = FALSE")
	Collection<Image> getAssetImages(@Param("assetId") Long assetId);
}

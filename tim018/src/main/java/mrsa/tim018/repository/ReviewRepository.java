package mrsa.tim018.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.renterID = :userId AND r.isClientWriting = TRUE")
	Collection<Review> getReviewsAboutRenter(@Param("userId") Long userId);
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.clientID = :userId AND r.isClientWriting = FALSE")
	Collection<Review> getReviewsAboutClient(@Param("userId") Long userId);
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.renterID = null AND r.assetId = :assetId")
	Collection<Review> getReviewsAboutAsset(@Param("assetId") Long userId);
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.status = 0")
	Collection<Review> getPendingReviews();
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.renterID = :userId AND r.isClientWriting = TRUE AND r.status = 1")
	Collection<Review> getAcceptedReviewsAboutRenter(@Param("userId") Long userId);
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.clientID = :userId AND r.isClientWriting = FALSE AND r.status = 1")
	Collection<Review> getAcceptedReviewsAboutClient(@Param("userId") Long userId);
	
	@Query("SELECT r "
			+ "FROM Review r "
			+ "WHERE r.renterID = null AND r.assetId = :assetId AND r.status = 1")
	Collection<Review> getAcceptedReviewsAboutAsset(@Param("assetId") Long userId);
}
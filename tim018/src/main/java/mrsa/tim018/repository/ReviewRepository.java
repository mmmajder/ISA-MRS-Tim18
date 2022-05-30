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
}
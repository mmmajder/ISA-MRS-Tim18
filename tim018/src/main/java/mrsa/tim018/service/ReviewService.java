package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Review;
import mrsa.tim018.model.ReviewType;
import mrsa.tim018.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;	

	public Review save(Review review) {
		return reviewRepository.save(review);
	}
	
	public Review findOne(Long id) {
		return reviewRepository.findById(id).orElse(null);
	}
	
	public ReviewType determineReviewType(Review review) {
		if (!review.isClientWriting())
			return ReviewType.FOR_CLIENT;
		else if (review.getRenterID() == null)
			return ReviewType.FOR_ASSET;
		return ReviewType.FOR_RENTER;
	}
	
	public List<Review> getReviewsAboutRenter(Long renterId){
		return (List<Review>) reviewRepository.getReviewsAboutRenter(renterId);
	}
	
	public List<Review> getReviewsAboutClient(Long clientId){
		return (List<Review>) reviewRepository.getReviewsAboutClient(clientId);
	}
}

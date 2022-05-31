package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Review;
import mrsa.tim018.model.ReviewType;
import mrsa.tim018.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReservationService reservationService;

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
	
	public Review acceptReview(Review review) {
		review.setStatus(RequestStatus.Accepted);
		reviewRepository.save(review);
		//TODO send email to Users
		//TODO if (!review.clientWriting && review.isComplaint) give 1 penalty to user
		
		return review;
	}
	
	// If Admin declines Review, the reference in Reservation should be also set to Null, so User could add new Review
	public Review declineReview(Review review) {
		review.setStatus(RequestStatus.Declined);
		reviewRepository.save(review);
		//TODO send email to Users
		
		Reservation reservation = reservationService.findOne(review.getReservationId());
		ReviewType type = determineReviewType(review);
		
		switch(type) {
		case FOR_ASSET : 
			reservation.setAssetReviewId(null);
			break;
		case FOR_RENTER :
			reservation.setRenterReviewId(null);
			break;
		case FOR_CLIENT : 
			reservation.setClientReviewId(null);
			break;
		default: break;
		}
		
		reservationService.save(reservation);
		return review;
	} 
	
	// if Client didn't show up, Review is automatically Accepted (if valid)
	public ResponseEntity<Review> acceptDidntShowUpReview(Review review, Reservation reservation) {
		if (reservation.getClientReviewId() != null)
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		review.setStatus(RequestStatus.Accepted);
		Long id = save(review).getID();
		reservation.setClientReviewId(id);
		reservationService.save(reservation);
		//TODO give 1 penalty to Client
		
		return new ResponseEntity<>(review, HttpStatus.OK);
	}
}

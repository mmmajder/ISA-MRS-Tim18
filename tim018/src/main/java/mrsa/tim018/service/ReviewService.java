package mrsa.tim018.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Review;
import mrsa.tim018.model.ReviewType;
import mrsa.tim018.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private EmailService emailService;
	
	public Review save(Review review) {
		return reviewRepository.save(review);
	}
	
	public Review findOne(Long id) {
		return reviewRepository.findById(id).orElse(null);
	}
	
	public Review findOnePending(Long id) {
		return reviewRepository.findPeningById(id).orElse(null);
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
	
	public List<Review> getReviewsAboutAsset(Long assetId){
		return (List<Review>) reviewRepository.getReviewsAboutAsset(assetId);
	}
	
	public List<Review> getAcceptedReviewsAboutRenter(Long renterId){
		return (List<Review>) reviewRepository.getAcceptedReviewsAboutRenter(renterId);
	}
	
	public List<Review> getAcceptedReviewsAboutClient(Long clientId){
		return (List<Review>) reviewRepository.getAcceptedReviewsAboutClient(clientId);
	}	
	
	public List<Review> getAcceptedReviewsAboutAsset(Long assetId){
		return (List<Review>) reviewRepository.getAcceptedReviewsAboutAsset(assetId);
	}
	
	public double getAssetRating(Long assetId){
		List<Review> reviews = (List<Review>) reviewRepository.getReviewsAboutAsset(assetId)
				.stream().filter(r -> r.getStatus() == RequestStatus.Accepted).collect(Collectors.toList());
		
		return calculateRating(reviews);
	}
	
	public double getRenterRating(Long renterId){
		List<Review> reviews = (List<Review>) reviewRepository.getReviewsAboutRenter(renterId)
				.stream().filter(r -> r.getStatus() == RequestStatus.Accepted).collect(Collectors.toList());
		
		return calculateRating(reviews);
	}
	
	public double getClientRating(Long clientId){
		List<Review> reviews = (List<Review>) reviewRepository.getReviewsAboutClient(clientId)
				.stream().filter(r -> r.getStatus() == RequestStatus.Accepted).collect(Collectors.toList());
		
		return calculateRating(reviews);
	}
	
	private double calculateRating(List<Review> reviews) {
		double rating = 0;
		int size = reviews.size();
		
		if (size != 0) {
			rating = reviews.stream().map(r -> r.getRating()).reduce(0, Integer::sum);
			rating = rating / size;
		}
		
		return rating;
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
	
	public List<Review> getPendingReviewsFromClients() {
		List<Review> retData = (List<Review>) reviewRepository.getPendingComplaintsAboutAsset();
		retData.addAll(reviewRepository.getPendingComplaintsAboutRenter());
		return retData;
	}

	public List<Review> getPendingPointReviewsFromRenters() {
		return  (List<Review>) reviewRepository.getPendingComplaintsCollectionAboutClient();
	}

	public List<Review> getPendingReviewsNotComplaints() {
		return (List<Review>) reviewRepository.getPendingReviewsNotComplaints();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = ObjectOptimisticLockingFailureException.class)
	public Review acceptDeclineReview(Long id, Boolean isAccepted) {
		Review review = updateReviewStatus(id, isAccepted);
		
		Client client = userService.findClient(review.getClientID());
		
		//sendMailChangeReviewStatus(isAccepted, review, client);
		return review;
	}

	private void sendMailChangeReviewStatus(Boolean isAccepted, Review review, Client client) {
		try {
			Renter renter;
			if (review.getRenterID()==null) {
				Asset asset = assetService.findById(review.getAssetId());
				renter = asset.getRenter();
			} else {
				renter = (Renter) userService.findOne(review.getRenterID());
			}
			emailService.sendReviewMail(review, client, renter, isAccepted);
		} catch (Exception e) {
			throw new ObjectOptimisticLockingFailureException("No review found", null);
		}
	}

	private Review updateReviewStatus(Long id, Boolean isAccepted) {
		Review review = findOnePending(id);
		if (review != null) {
			if (isAccepted) {
				review.setStatus(RequestStatus.Accepted); 
			} else { 
				review.setStatus(RequestStatus.Declined); 
			}
			save(review);
		} else {
			throw new ObjectOptimisticLockingFailureException("No review found", null);
		}
		return review;
	}
}

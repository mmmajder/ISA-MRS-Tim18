package mrsa.tim018.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Review;
import mrsa.tim018.model.ReviewType;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.ReservationService;
import mrsa.tim018.service.ReviewService;
import mrsa.tim018.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private AssetService assetService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Review> getReview(@PathVariable Long id) {
		Review r = reviewService.findOne(id);
		
		if (r == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// so more technicaly smart users wouldn't be able to 'hack' and see the message before it was accepted
		if (r.getStatus() == RequestStatus.Pending) {
			r.setText("");
			r.setRating(0);
		}
			
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{reservationId}", consumes = "application/json")
	public ResponseEntity<Review> createReview(@PathVariable Long reservationId, @RequestBody Review review) {
		Reservation reservation = reservationService.findOne(reservationId);
		
		if (reservation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		review.setDeleted(false);
		review.setReservationId(reservationId);
		
		// if Client didn't show up, Review is automatically Accepted (if valid)
		if (review.isDidntShowUp())
			return reviewService.acceptDidntShowUpReview(review, reservation);
		
		review.setStatus(RequestStatus.Pending);
		Long id;
		ReviewType type = reviewService.determineReviewType(review);
		
		switch(type) {
		case FOR_ASSET : 
			if (reservation.getAssetReviewId() != null)
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			id = reviewService.save(review).getID();
			reservation.setAssetReviewId(id);
			break;
		case FOR_RENTER :
			if (reservation.getRenterReviewId() != null)
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			id = reviewService.save(review).getID();
			reservation.setRenterReviewId(id);
			break;
		case FOR_CLIENT : 
			if (reservation.getClientReviewId() != null)
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			id = reviewService.save(review).getID();
			reservation.setClientReviewId(id);
			break;
		default: return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		reservationService.save(reservation);
		
		return new ResponseEntity<>(review, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<List<Review>> getReviews(@PathVariable Long userId) {
		User u = userService.findOne(userId);
		if (u == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		List<Review> r;
		
		if (u.getUserType() == UserType.Client) {
			r = reviewService.getReviewsAboutClient(userId);
		}else {
			r = reviewService.getReviewsAboutRenter(userId);
		}
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	@GetMapping(value = "/asset/{assetId}")
	public ResponseEntity<List<Review>> getAssetReviews(@PathVariable Long assetId) {
		Asset a = assetService.findOne(assetId);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		List<Review> r = reviewService.getReviewsAboutAsset(assetId);
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
}

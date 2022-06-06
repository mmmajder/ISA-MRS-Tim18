package mrsa.tim018.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.MailsDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Review;
import mrsa.tim018.model.ReviewType;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.EmailService;
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

	@Autowired
	private EmailService emailService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Review> getReview(@PathVariable Long id) {
		Review r = reviewService.findOne(id);

		if (r == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		// so more technicaly smart users wouldn't be able to 'hack' and see the message
		// before it was accepted
		if (r.getStatus() == RequestStatus.Pending) {
			r.setText("");
			r.setRating(0);
		}

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping(value = "/clients/pending")
	public ResponseEntity<List<Review>> getAllClientPendingReviews() {
		List<Review> data = reviewService.getPendingReviewsFromClients();
		if (data == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/renters/pending")
	public ResponseEntity<List<Review>> getAllRenterPendingReviews() {
		List<Review> data = reviewService.getPendingPointReviewsFromRenters();
		if (data == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(data, HttpStatus.OK);
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

		switch (type) {
		case FOR_ASSET:
			if (reservation.getAssetReviewId() != null)
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			id = reviewService.save(review).getID();
			reservation.setAssetReviewId(id);
			break;
		case FOR_RENTER:
			if (reservation.getRenterReviewId() != null)
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			id = reviewService.save(review).getID();
			reservation.setRenterReviewId(id);
			break;
		case FOR_CLIENT:
			if (reservation.getClientReviewId() != null)
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			id = reviewService.save(review).getID();
			reservation.setClientReviewId(id);
			break;
		default:
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		reservationService.save(reservation);

		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<List<Review>> getReviews(@PathVariable Long userId,
			@RequestParam("acceptedOnly") Boolean acceptedOnly) {
		User u = userService.findOne(userId);
		if (u == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<Review> r;

		if (u.getUserType() == UserType.Client) {
			r = (acceptedOnly == true) ? reviewService.getAcceptedReviewsAboutClient(userId)
					: reviewService.getReviewsAboutClient(userId);
		} else {
			r = (acceptedOnly == true) ? reviewService.getAcceptedReviewsAboutRenter(userId)
					: reviewService.getReviewsAboutRenter(userId);
		}

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping(value = "/asset/{assetId}")
	public ResponseEntity<List<Review>> getAssetReviews(@PathVariable Long assetId,
			@RequestParam("acceptedOnly") Boolean acceptedOnly) {
		Asset a = assetService.findOne(assetId);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<Review> r = (acceptedOnly == true) ? reviewService.getAcceptedReviewsAboutAsset(assetId)
				: reviewService.getReviewsAboutAsset(assetId);

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping(value = "/userRating/{userId}")
	public ResponseEntity<Double> getRenterRating(@PathVariable Long userId) {
		User u = userService.findOne(userId);
		if (u == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		double r;

		if (u.getUserType() == UserType.Client) {
			r = reviewService.getClientRating(userId);
		} else {
			r = reviewService.getRenterRating(userId);
		}

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping(value = "/assetRating/{assetId}")
	public ResponseEntity<Double> getAssetRating(@PathVariable Long assetId) {
		Asset a = assetService.findOne(assetId);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		double r = reviewService.getAssetRating(assetId);

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@PutMapping(value = "/cancelClientsComplaint/{id}")
	public ResponseEntity<Review> cancelClientsComplaint(@PathVariable Long id) {
		Review review = reviewService.findOne(id);
		if (review == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		review.setStatus(RequestStatus.Declined);
		reviewService.save(review);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PutMapping(value = "/sendCommentOnComplaint/{id}")
	public ResponseEntity<Review> sendCommentOnComplaint(@PathVariable Long id, @RequestBody MailsDTO reqData) {
		Review review = reviewService.findOne(id);
		if (review == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		review.setStatus(RequestStatus.Accepted);
		reviewService.save(review);
		try {
			emailService.sendMailsClientsComplaint(reqData.getMailClient(), reqData.getMailRenter(),
					review.getClientID());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PutMapping(value = "/addPoint/{id}")
	public ResponseEntity<Review> addPoint(@PathVariable Long id) {
		Review review = reviewService.findOne(id);
		if (review == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		review.setStatus(RequestStatus.Accepted);
		reviewService.save(review);
		Client client = userService.findClient(review.getClientID());
		client.setPenaltyPoints(client.getPenaltyPoints() + 1);
		userService.saveClient(client);
		Renter renter = (Renter) userService.findOne(review.getRenterID());
		try {
			emailService.sendPointMail(review, client, renter, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PutMapping(value = "/declineAddPoint/{id}")
	public ResponseEntity<Review> declineAddPoint(@PathVariable Long id) {
		Review review = reviewService.findOne(id);
		if (review == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		review.setStatus(RequestStatus.Declined);
		reviewService.save(review);
		Client client = (Client) userService.findOne(review.getClientID());
		Renter renter = (Renter) userService.findOne(review.getRenterID());
		try {
			emailService.sendPointMail(review, client, renter, false);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PutMapping(value = "/acceptdeclineReview/{id}")
	public ResponseEntity<Review> acceptdeclineReview(@PathVariable Long id, @RequestBody Boolean isAccepted) {
		Review review = reviewService.findOne(id);
		if (review == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (isAccepted) {
			review.setStatus(RequestStatus.Accepted); 
		} else {
			review.setStatus(RequestStatus.Declined);
		}
		reviewService.save(review);
		Client client = userService.findClient(review.getClientID());
		userService.saveClient(client);
		Renter renter = (Renter) userService.findOne(review.getRenterID());
		try {
			emailService.sendReviewMail(review, client, renter, isAccepted);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@GetMapping(value = "/pendingNonComplaint")
	public ResponseEntity<List<Review>> getPendingReviews() {
		List<Review> pendingReviews = reviewService.getPendingReviewsNotComplaints();
		if (pendingReviews.size() == 0)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(pendingReviews, HttpStatus.OK);
	}
}

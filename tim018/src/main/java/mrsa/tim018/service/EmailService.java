package mrsa.tim018.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mrsa.tim018.dto.RegisterAdminRequestDTO;
import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Review;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.model.User;
import mrsa.tim018.utils.EmailContentUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	@Autowired 
	private ClientService clientService;
	
	private String siteURL = "http://localhost:3000";
	@Async
	public void sendNotificaitionAsync(User user) throws MessagingException, UnsupportedEncodingException  {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("Please verify your registration");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		 
		String content = EmailContentUtils.getVerificationContent(); 
		String verifyURL = siteURL + "/verify/" + user.getVerificationCode();
		content = content.replace("[[URL]]", verifyURL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message);

		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendRegistrationResponseAsync(RequestStatus status, String adminExpl) throws MessagingException, UnsupportedEncodingException  {
		MimeMessage message = javaMailSender.createMimeMessage();
		if (status == RequestStatus.Accepted)
			message.setSubject("Hakuna Matata profile registration accepted");
		else 
			message.setSubject("Hakuna Matata profile registration denied");
		
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String verifyURL;
		String content = EmailContentUtils.getRegistrationResponseContent(status, adminExpl); ;
		if (status == RequestStatus.Accepted) {
			verifyURL = siteURL + "/login";
		} else {
			verifyURL = siteURL + "/login#";
		}

		content = content.replace("[[URL]]", verifyURL);
		
		
		helper.setText(content, true);
		
		javaMailSender.send(message);

		System.out.println("Email poslat!");
	} 
	
	@Async
	public void sendDeleteProfileResponseAsync(RequestStatus status, String adminExpl) throws MessagingException, UnsupportedEncodingException  {
		MimeMessage message = javaMailSender.createMimeMessage();
		if (status == RequestStatus.Accepted)
			message.setSubject("Hakuna Matata profile delete accepted");
		else 
			message.setSubject("Hakuna Matata profile delete denied");
		
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));

		String verifyURL;
		String content = EmailContentUtils.getDeleteProfileResponseContent(status, adminExpl); ;
		if (status == RequestStatus.Accepted) {
			verifyURL = siteURL + "/login";
		} else {
			verifyURL = siteURL + "/login#";
		}

		content = content.replace("[[URL]]", verifyURL);
		
		
		helper.setText(content, true);
		
		javaMailSender.send(message);

		System.out.println("Email poslat!");
	}

	@Async
	public void sendReservationSuccessfull(Reservation reservation) throws MessagingException, UnsupportedEncodingException  {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("Hakuna Matata new reservation");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String content = EmailContentUtils.getNewReservationContent(reservation); 
		String URL = siteURL + "/allReservations";
		content = content.replace("[[URL]]", URL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message);
	}
	
	@Async
	public void sendReservationSuccessfullAdmin(RegisterAdminRequestDTO registerAdminRequestDTO) throws MessagingException, UnsupportedEncodingException  {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("Hakuna Matata Admin account created");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String content = EmailContentUtils.getNewAdminContent(registerAdminRequestDTO); 
		String URL = siteURL + "/login";
		content = content.replace("[[URL]]", URL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message); 
	}
	public void notifySubscribers(List<Subscription> subscriptions, AppointmentCreationDTO offer) throws MessagingException, UnsupportedEncodingException  {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("Hakuna Matata new special offer");

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		String URL = siteURL + "/resorts/" + offer.getAssetId();

		for (Subscription subscription : subscriptions) {
			String content = EmailContentUtils.notifySubscribers(subscription, offer); 
			content = content.replace("[[URL]]", URL);
			helper.setText(content, true);
			javaMailSender.send(message);
		}
	}
	@Async
	public void sendMailsClientsComplaint(String mailClient, String mailRenter, Long clientId) throws MessagingException {
		
		Client client = clientService.findOne(clientId);
		
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("[Hakuna Matata] Clients complaint");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String content = EmailContentUtils.getClientsComplaintResponseToClient(mailClient, client); 
		String URL = siteURL + "/login";
		content = content.replace("[[URL]]", URL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message); 
		


		MimeMessage messageRenter = javaMailSender.createMimeMessage();
		messageRenter.setSubject("[Hakuna Matata] Clients complaint");
		
		MimeMessageHelper helperRenter = new MimeMessageHelper(messageRenter, true);
		helperRenter.setTo("isamrs018@gmail.com");
		helperRenter.setFrom(env.getProperty("spring.mail.username"));
		String contentRenter = EmailContentUtils.getClientsComplaintResponseToRenter(mailRenter, client); 
		
		helperRenter.setText(contentRenter, true);
		
		javaMailSender.send(messageRenter); 
		
	}
	
	public void sendPointMail(Review review, Client client, Renter renter, boolean isAccepted) throws MessagingException {
		sendPointMail(review, client, renter, "client", isAccepted);
		sendPointMail(review, client, renter, "renter", isAccepted);
	}
	
	@Async
	private void sendPointMail(Review review, Client client, Renter renter, String receiver, boolean isAccepted) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("[Hakuna Matata] Renters complaint");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String content = EmailContentUtils.getAddPointMail(review, client, renter, receiver, isAccepted); 
		String URL = siteURL + "/login";
		content = content.replace("[[URL]]", URL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message);
		
	}

	public void sendReviewMail(Review review, Client client, Renter renter, Boolean isAccepted) throws MessagingException {
		if (isAccepted) {
			sendReviewMail(review, client, renter, isAccepted, "client");
			sendReviewMail(review, client, renter, isAccepted, "renter");
		} else {
			if (review.isClientWriting()) {
				sendReviewMail(review, client, renter, isAccepted, "client");
			}
			else {
				sendReviewMail(review, client, renter, isAccepted, "renter");
			}
		}
		
	}
	
	@Async
	private void sendReviewMail(Review review, Client client, Renter renter, Boolean isAccepted, String receiver) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("[Hakuna Matata] Renters complaint");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String content = EmailContentUtils.getReviewMail(review, client, renter, isAccepted, receiver); 
		String URL = siteURL + "/login";
		content = content.replace("[[URL]]", URL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message);
	}
}

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
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
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
}

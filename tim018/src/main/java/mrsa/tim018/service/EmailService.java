package mrsa.tim018.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.User;
import mrsa.tim018.utils.EmailContentUtils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	
	@Async
	public void sendNotificaitionAsync(User user) throws MessagingException, UnsupportedEncodingException  {
		//SimpleMailMessage mail = new SimpleMailMessage();
		
		
//		//mail.setTo(user.getEmail());
//		mail.setTo("isamrs018@gmail.com");
//		mail.setFrom(env.getProperty("spring.mail.username"));
//		mail.setSubject("Please verify your registration");
//		String siteURL = "http://localhost:3000";
		
//		String content = "Dear [[name]],<br>"
//		            + "Please click the link below to verify your registration:<br>"
//		            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
//		            + "Thank you,<br>"
//		            + "Hakuna matata.";
		
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("Please verify your registration");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("isamrs018@gmail.com");
		helper.setFrom(env.getProperty("spring.mail.username"));
		
		String siteURL = "http://localhost:3000";
		
		String content = EmailContentUtils.getVerificationContent(); 
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
		content = content.replace("[[URL]]", verifyURL);
		
		helper.setText(content, true);
		
		javaMailSender.send(message);

		System.out.println("Email poslat!");
	}

}

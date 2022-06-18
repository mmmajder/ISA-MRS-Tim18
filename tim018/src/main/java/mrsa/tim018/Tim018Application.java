package mrsa.tim018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Tim018Application {

	public static void main(String[] args) {
		SpringApplication.run(Tim018Application.class, args);
	}

} 

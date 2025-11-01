package hu.unideb.inf.suitup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuitupApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuitupApplication.class, args);
	}

}

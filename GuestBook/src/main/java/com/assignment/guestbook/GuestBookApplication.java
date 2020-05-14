package com.assignment.guestbook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.assignment.guestbook.controllers.GuestBookController;


/**
 * GuestBookApplication, Main Class for Running Spring boot app
 * @author Manish
 *
 */
@SpringBootApplication(scanBasePackages= {"com.assignment.guestbook"})
public class GuestBookApplication {

	private static final Logger LOGGER = LogManager.getLogger(GuestBookApplication.class);
	/**
	 * Main Method used to Start the Spring Boot App
	 * @param args
	 */
	public static void main(String[] args) {
		
		LOGGER.info("About to RUN GuestBook SpringBoot app");
		SpringApplication.run(GuestBookApplication.class, args);
	}
	
	

}

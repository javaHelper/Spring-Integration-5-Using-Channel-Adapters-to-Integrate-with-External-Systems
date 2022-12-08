package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqGlobalmanticsReservationServiceApplication implements CommandLineRunner{
	private static final Logger logger = LogManager.getLogger(RabbitmqGlobalmanticsReservationServiceApplication.class);
			
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqGlobalmanticsReservationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting Foo Reservation Listener");
	}
}

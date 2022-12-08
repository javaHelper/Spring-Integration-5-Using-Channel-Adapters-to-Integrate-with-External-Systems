package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaGlobomaticsReservationServiceApplication implements CommandLineRunner{

	private static final Logger logger = LogManager.getLogger(KafkaGlobomaticsReservationServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaGlobomaticsReservationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting Foo Reservation Listener");
	}
}

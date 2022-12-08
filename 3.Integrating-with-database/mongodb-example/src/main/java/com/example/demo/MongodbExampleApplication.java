package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;

@SpringBootApplication
public class MongodbExampleApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MongodbExampleApplication.class, args);
	}

	@Autowired
	private ReservationService service;
	
	@Override
	public void run(String... args) throws Exception {
		List<Reservation> reservations = Arrays.asList(
				new Reservation("User 1", "None"),
				new Reservation("User 2", "None"),
				new Reservation("User 3", "None"),
				new Reservation("User 4", "None"),
				new Reservation("User 5", "None"),
				new Reservation("User 6", "None"));
		reservations.forEach(reservation -> {
			service.publishReservation(reservation);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}

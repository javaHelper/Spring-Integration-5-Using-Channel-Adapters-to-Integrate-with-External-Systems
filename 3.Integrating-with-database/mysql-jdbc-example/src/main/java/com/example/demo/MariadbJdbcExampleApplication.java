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
public class MariadbJdbcExampleApplication implements CommandLineRunner {

	@Autowired
    private ReservationService service;
	
	public static void main(String[] args) {
		SpringApplication.run(MariadbJdbcExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Reservation> reservations = Arrays.asList(
                new Reservation(1L, "User 1"),
                new Reservation(2L, "User 2"),
                new Reservation(3L, "User 3"),
                new Reservation(4L, "User 4"),
                new Reservation(5L, "User 5"));
        reservations.forEach(reservation -> {
        	service.createReservation(reservation);
        	try {
        		Thread.sleep(1000);
			} catch (InterruptedException e) {
        		e.printStackTrace();
			}
		});
	}

}

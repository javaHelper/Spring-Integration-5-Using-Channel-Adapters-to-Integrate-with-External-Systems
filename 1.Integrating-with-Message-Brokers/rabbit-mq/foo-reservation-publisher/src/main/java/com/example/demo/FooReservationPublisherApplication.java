package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.FooReservation;
import com.example.demo.service.FooReservationService;

@SpringBootApplication
public class FooReservationPublisherApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FooReservationPublisherApplication.class, args);
	}

	@Autowired
    private FooReservationService service;
	
	@Override
	public void run(String... args) throws Exception {
		List<FooReservation> reservations = Arrays.asList(
                new FooReservation(1, "User 1"),
                new FooReservation(2, "User 2"),
                new FooReservation(3, "User 3"),
                new FooReservation(4, "User 4"),
                new FooReservation(5, "User 5"),
                new FooReservation(6, "User 6"));
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

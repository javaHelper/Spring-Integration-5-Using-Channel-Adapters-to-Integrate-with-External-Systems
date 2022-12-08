package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.BazReservation;
import com.example.demo.service.BazReservationService;

@SpringBootApplication
public class KafkaReservationPublisherApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(KafkaReservationPublisherApplication.class, args);
	}

	@Autowired
    private BazReservationService service;
	
	@Override
	public void run(String... args) throws Exception {
		List<BazReservation> reservations = Arrays.asList(
                new BazReservation(1, "User 1"),
                new BazReservation(2, "User 2"),
                new BazReservation(3, "User 3"),
                new BazReservation(4, "User 4"),
                new BazReservation(5, "User 5"),
                new BazReservation(6, "User 6"));
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

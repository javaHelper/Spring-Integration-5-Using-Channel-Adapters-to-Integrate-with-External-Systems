package com.example.demo.config;

import org.springframework.integration.annotation.MessagingGateway;

import com.example.demo.model.BazReservation;

@MessagingGateway(defaultRequestChannel = "reservationChannel")
public interface BazReservationGateway {
    void publishReservation(BazReservation reservation);
}

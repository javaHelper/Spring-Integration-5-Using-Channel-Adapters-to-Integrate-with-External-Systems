package com.example.demo.config;

import org.springframework.integration.annotation.MessagingGateway;

import com.example.demo.model.FooReservation;

@MessagingGateway(defaultRequestChannel = "reservationChannel")
public interface FooReservationGateway {
    void publishReservation(FooReservation fooReservation);
}

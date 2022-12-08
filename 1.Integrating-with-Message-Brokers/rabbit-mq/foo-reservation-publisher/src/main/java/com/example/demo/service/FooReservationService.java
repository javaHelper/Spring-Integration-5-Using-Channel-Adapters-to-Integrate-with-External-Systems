package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.FooReservationGateway;
import com.example.demo.model.FooReservation;

@Service
public class FooReservationService {
    private static final Logger logger = LogManager.getLogger(FooReservationService.class);

    @Autowired
    private FooReservationGateway gateway;

    public void publishReservation(FooReservation reservation) {
        logger.info("Publishing reservation {} for user {}", reservation.getId(), reservation.getName());
        gateway.publishReservation(reservation);
    }
}

package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.BazReservationGateway;
import com.example.demo.model.BazReservation;

@Service
public class BazReservationService {
    private static final Logger logger = LogManager.getLogger(BazReservationService.class);

    @Autowired
    private BazReservationGateway gateway;

    public void publishReservation(BazReservation reservation) {
        logger.info("Publishing reservation {} for user {}", reservation.getId(), reservation.getName());
        gateway.publishReservation(reservation);
    }
}

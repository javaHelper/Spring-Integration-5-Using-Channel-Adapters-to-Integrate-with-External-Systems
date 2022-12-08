package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reservation;

@Service
public class ReservationListener {
    private static final Logger logger = LogManager.getLogger(ReservationListener.class);

    @ServiceActivator(inputChannel = "reservationFromCustomChannel")
    public void handleReservation(Reservation reservation) {
        logger.info("Received reservation: {}", reservation);
    }
}

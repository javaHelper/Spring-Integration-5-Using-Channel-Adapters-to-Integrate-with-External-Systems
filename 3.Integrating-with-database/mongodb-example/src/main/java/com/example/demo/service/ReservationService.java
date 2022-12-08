package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.OutboundChannelAdapterConfig;
import com.example.demo.model.Reservation;

@Service
public class ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationService.class);

    @Autowired
    private OutboundChannelAdapterConfig.MongoReservationGateway gateway;

    public void publishReservation(Reservation reservation) {
        logger.info("Publishing reservation: {}", reservation);
        gateway.publishReservation(reservation);
    }
}

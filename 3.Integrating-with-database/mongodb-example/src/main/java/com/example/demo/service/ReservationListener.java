package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import com.example.demo.config.OutboundChannelAdapterConfig;
import com.example.demo.config.OutboundGatewayConfig;
import com.example.demo.model.Reservation;

@Service
public class ReservationListener {
    private static final Logger logger = LogManager.getLogger(ReservationListener.class);

    @Autowired
    private OutboundChannelAdapterConfig.UpdateReservationGateway updateReservationGateway;

    @Autowired
    private OutboundGatewayConfig.QueryReservationGateway queryReservationGateway;

    @ServiceActivator(inputChannel = "reservationFromMongoChannel")
    public void handleReservation(Reservation reservation) {
        logger.info("Received Reservation: {}", reservation);

        reservation.setStatus("Processed");
        updateReservationGateway.updateReservation(reservation);

        Reservation updatedReservation = queryReservationGateway.query("{id: '" + reservation.getId() + "'}");
        logger.info("Updated Reservation: {}", updatedReservation);
    }
}

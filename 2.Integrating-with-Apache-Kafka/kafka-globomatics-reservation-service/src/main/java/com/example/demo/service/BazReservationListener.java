package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.example.demo.config.KafkaOutboundGatewayConfig;
import com.example.demo.model.BazReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BazReservationListener {
    private static final Logger logger = LogManager.getLogger(BazReservationListener.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaOutboundGatewayConfig.AddressGateway addressGateway;

    @SuppressWarnings("rawtypes")
	@ServiceActivator(inputChannel = "reservationFromKafka")
    public void handleMessage(String message) throws JsonProcessingException {
        BazReservation reservation = objectMapper.readValue(message, BazReservation.class);
        logger.info("Reservation: {}", reservation);

        Message address = addressGateway.getAddress(Long.toString(reservation.getId()));
        logger.info("Address: {}", address.getPayload());
    }
}

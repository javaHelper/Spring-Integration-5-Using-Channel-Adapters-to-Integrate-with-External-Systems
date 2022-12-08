package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.example.demo.model.Reservation;

@Configuration
public class CustomOutboundChannelAdapterConfig {
    @Bean
    public MessageChannel reservationToCustomChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "reservationToCustomChannel")
    public MessageHandler customMessageHandler() {
        return new DirectoryMonitorMessageHandler("reservations");
    }

    @MessagingGateway(defaultRequestChannel = "reservationToCustomChannel")
    public interface CreateReservationGateway {
        void createReservation(Reservation reservation);
    }

}

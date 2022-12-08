package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.example.demo.model.Reservation;

@Configuration
public class HttpOutboundChannelAdapterConfig {

    @Bean
    public MessageChannel toReservationServiceChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "toReservationServiceChannel")
    public MessageHandler postReservationToService() {
        // Create a new HttpRequestExecutingMessageHandler
        HttpRequestExecutingMessageHandler messageHandler = new HttpRequestExecutingMessageHandler(
                "http://localhost:7080/reservation");
        messageHandler.setHttpMethod(HttpMethod.POST);

        // Setup our reply configuration - we do not expect a reply
        messageHandler.setExpectReply(false);

        return messageHandler;
    }

    @MessagingGateway(defaultRequestChannel = "toReservationServiceChannel")
    public interface PublishReservationGateway {
        void publishReservation(Message<Reservation> message);
    }
}

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mongodb.outbound.MongoDbOutboundGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.example.demo.model.Reservation;

@Configuration
public class OutboundGatewayConfig {
    @Bean
    public MessageChannel getReservationChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel getReservationReplyChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "getReservationChannel")
    public MessageHandler mongoDbOutboundGateway(MongoTemplate template) {
        MongoDbOutboundGateway gateway = new MongoDbOutboundGateway(template);
        gateway.setCollectionNameExpressionString("'reservations'");
        gateway.setQueryExpressionString("payload");
        gateway.setExpectSingleResult(true);
        gateway.setEntityClass(Reservation.class);
        gateway.setOutputChannelName("getReservationReplyChannel");
        return gateway;
    }

    @MessagingGateway(defaultRequestChannel = "getReservationChannel", defaultReplyChannel = "getReservationReplyChannel")
    public interface QueryReservationGateway {
        Reservation query(String query);
    }
}

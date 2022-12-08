package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.mongodb.inbound.MongoDbMessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.example.demo.model.Reservation;

@Configuration
public class InboundChannelAdapterConfig {
    @Bean
    public MessageChannel reservationFromMongoChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel reservationListFromMongoChannel() {
        return new DirectChannel();
    }

    @SuppressWarnings("unchecked")
	@Splitter(inputChannel = "reservationListFromMongoChannel", outputChannel = "reservationFromMongoChannel")
    public List<Reservation> splitter(Message message) {
        return (List)message.getPayload();
    }

    @SuppressWarnings("rawtypes")
	@Bean
    @InboundChannelAdapter(value = "reservationListFromMongoChannel", poller = @Poller(fixedDelay = "3000"))
    public MessageSource mongoMessageSource(MongoTemplate template) {
        MongoDbMessageSource messageSource = new MongoDbMessageSource(template,
                new LiteralExpression("{'status' : 'None'}"));
        messageSource.setCollectionNameExpression(new LiteralExpression("reservations"));
        messageSource.setEntityClass(Reservation.class);
        return messageSource;
    }
}

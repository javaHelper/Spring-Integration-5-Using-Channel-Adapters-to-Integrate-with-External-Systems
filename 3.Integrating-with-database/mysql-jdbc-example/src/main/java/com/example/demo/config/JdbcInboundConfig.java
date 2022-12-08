package com.example.demo.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.example.demo.model.Reservation;

@Configuration
public class JdbcInboundConfig {
    @Bean
    public MessageChannel newReservationChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel newReservationListChannel() {
        return new DirectChannel();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Splitter(inputChannel = "newReservationListChannel", outputChannel = "newReservationChannel")
    public List<Reservation> splitter(Message message) {
        return (List)message.getPayload();
    }

    @Bean
    @InboundChannelAdapter(value = "newReservationListChannel", poller = @Poller(fixedDelay="1000"))
    public MessageSource<?> checkDbForReservations(DataSource dataSource) {
        JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource,
                "SELECT * FROM reservation where status = 0");
        adapter.setRowMapper((rs, index) -> new Reservation(rs.getLong("id"), rs.getString("name")));
        adapter.setUpdateSql("update reservation set status = 1 where id in (:id)");
        return adapter;
    }
}

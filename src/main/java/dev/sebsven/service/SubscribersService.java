package dev.sebsven.service;

import dev.sebsven.domain.Subscriber;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribersService {

    private final JdbcClient jdbcClient;

    public SubscribersService(@Qualifier("subscriberJdbcClient") JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Subscriber> getSubscribers() {
        return jdbcClient.sql("SELECT id, name, email FROM Subscribers")
                .query(Subscriber.class)
                .list();

    }
}

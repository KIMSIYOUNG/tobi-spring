package com.example.tobibookspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();

        return new UserDao(connectionMaker);
    }

    @Bean
    public UserDao userCountingDao() {
        ConnectionMaker connectionMaker = new CountingConnectionMaker(new DConnectionMaker());

        return new UserDao(connectionMaker);
    }
}

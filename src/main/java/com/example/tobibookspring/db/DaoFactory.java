package com.example.tobibookspring.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();

        return new UserDao(connectionMaker, jdbcContext());
    }

    @Bean
    public JdbcContext jdbcContext() {
        JdbcContext jdbcContext = new JdbcContext(new DConnectionMaker());

        return jdbcContext;
    }

    @Bean
    public UserDao userCountingDao() {
        ConnectionMaker connectionMaker = new CountingConnectionMaker(new DConnectionMaker());

        return new UserDao(connectionMaker, jdbcContext());
    }
}

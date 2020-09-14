package com.example.tobibookspring;

public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();

        return new UserDao(connectionMaker);
    }

    public UserDao userCountingDao() {
        ConnectionMaker connectionMaker = new CountingConnectionMaker(new DConnectionMaker());

        return new UserDao(connectionMaker);
    }
}

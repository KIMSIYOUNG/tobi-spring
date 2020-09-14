package com.example.tobibookspring;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    private int count = 0;
    private final ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection openConnection() throws ClassNotFoundException, SQLException {
        count ++;
        return realConnectionMaker.openConnection();
    }

    public int getCount() {
        return count;
    }
}

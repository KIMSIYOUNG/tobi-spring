package com.example.tobibookspring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    public Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        return DriverManager.getConnection(
            "jdbc:h2:tcp://localhost/~/test", "sa", ""
        );
    }
}

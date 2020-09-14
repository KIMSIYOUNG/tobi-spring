package com.example.tobibookspring;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    Connection openConnection() throws ClassNotFoundException, SQLException;
}

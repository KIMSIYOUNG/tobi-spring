package com.example.tobibookspring.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    Connection openConnection() throws ClassNotFoundException, SQLException;
}

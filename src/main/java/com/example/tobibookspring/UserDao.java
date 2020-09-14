package com.example.tobibookspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.openConnection();

        PreparedStatement ps = connection.prepareStatement(
            "insert into users(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.openConnection();

        PreparedStatement ps = connection.prepareStatement(
            "select * from users where id = ?"
        );
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        connection.close();

        return user;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.openConnection();

        PreparedStatement ps = connection.prepareStatement(
            "delete from users"
        );

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public int count() throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.openConnection();

        PreparedStatement ps = connection.prepareStatement(
            "select count(*) from users"
        );

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        ps.close();
        connection.close();

        return count;
    }
}

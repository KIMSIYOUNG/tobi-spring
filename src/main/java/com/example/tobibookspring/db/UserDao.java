package com.example.tobibookspring.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final ConnectionMaker connectionMaker;
    private final JdbcContext jdbcContext;

    public UserDao(ConnectionMaker connectionMaker, JdbcContext jdbcContext) {
        this.connectionMaker = connectionMaker;
        this.jdbcContext = jdbcContext;
    }

    public void add(final User user) {
        jdbcContext.workWithStatementStrategy((con) -> {
            PreparedStatement ps = con.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
            );
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            return ps;
        });
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

    public void deleteAll() {
        executeSql("delete from users");
    }

    private void executeSql(String sql) {
        jdbcContext.workWithStatementStrategy((con) -> con.prepareStatement(sql));
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

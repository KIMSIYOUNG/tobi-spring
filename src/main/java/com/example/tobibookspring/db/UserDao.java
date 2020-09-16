package com.example.tobibookspring.db;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    };


    public void add(final User user) {
        jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)",
            user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?",
            new Object[] {id}, rowMapper);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users order by id", rowMapper);
    }
}

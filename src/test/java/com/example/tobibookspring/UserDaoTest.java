package com.example.tobibookspring;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class UserDaoTest {
    @Autowired
    private ApplicationContext context;

    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        this.dao = this.context.getBean("userDao", UserDao.class);

        this.user1 = new User("AAA", "BBB", "CCC");
        this.user2 = new User("BBB", "DDD", "DDD");
        this.user3 = new User("CCC", "EEE", "EEE");
    }

    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.count()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.count()).isEqualTo(2);

        User findUser1 = dao.get(user1.getId());
        assertThat(findUser1).isEqualToComparingFieldByField(user1);

        User findUser2 = dao.get(user2.getId());
        assertThat(findUser2).isEqualToComparingFieldByField(user2);
    }

    @Test
    void getUserFail() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.count()).isEqualTo(0);

        assertThatThrownBy(() -> dao.get("NOT_EXIST"))
            .isInstanceOf(Exception.class);
    }

    @Test
    void count() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.count()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.count()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.count()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.count()).isEqualTo(3);
    }
}
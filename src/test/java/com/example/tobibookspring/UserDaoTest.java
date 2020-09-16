package com.example.tobibookspring;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.tobibookspring.db.User;
import com.example.tobibookspring.db.UserDao;

@SpringBootTest(classes = UserDao.class)
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
    void addAndGet() {
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
    void getUserFail() {
        dao.deleteAll();
        assertThat(dao.count()).isEqualTo(0);

        assertThatThrownBy(() -> dao.get("NOT_EXIST"))
            .isInstanceOf(Exception.class);
    }

    @Test
    void count() {
        dao.deleteAll();
        assertThat(dao.count()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.count()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.count()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.count()).isEqualTo(3);
    }

    @Test
    public void getAll() {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0).hasSize(0);

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1).hasSize(1);
        assertThat(users1.get(0)).isEqualToComparingFieldByField(user1);

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2).hasSize(2);
        assertThat(users1.get(0)).isEqualToComparingFieldByField(user1);
        assertThat(users1.get(1)).isEqualToComparingFieldByField(user2);

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3).hasSize(3);
        assertThat(users1.get(0)).isEqualToComparingFieldByField(user1);
        assertThat(users1.get(1)).isEqualToComparingFieldByField(user2);
        assertThat(users1.get(2)).isEqualToComparingFieldByField(user3);
    }
}
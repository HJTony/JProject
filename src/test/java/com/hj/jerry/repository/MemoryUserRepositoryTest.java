package com.hj.jerry.repository;

import com.hj.jerry.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryUserRepositoryTest {

    MemoryUserRepository userRepositoy = new MemoryUserRepository();

    @AfterEach
    void afterEach() {
        userRepositoy.clearStore();
    }

    @Test
    void findAll() {
        User u1 = new User();
        User u2 = new User();
        u1.setName("user1");
        u2.setName("user2");

        userRepositoy.save(u1);
        userRepositoy.save(u2);

        List<User> userList = userRepositoy.findAll();

        boolean result = userList.containsAll(Arrays.asList(u1,u2));

        assertTrue(result);
    }

    @Test
    void save() {
        User u1 = new User();
        User u2 = new User();
        u1.setName("user1");
        u2.setName("user2");

        userRepositoy.save(u1);
        userRepositoy.save(u2);

        List<User> userList = userRepositoy.findAll();

        assertEquals(2, userList.size());
    }

    @Test
    void findById() {
        User u1 = new User();
        String userName = "user1";
        u1.setName(userName);

        userRepositoy.save(u1);

        User result = userRepositoy.findById(1L).get();

        assertEquals(result.getId(), 1L);
    }

    @Test
    void findByName() {
        User u1 = new User();
        String userName = "user1";
        u1.setName(userName);

        userRepositoy.save(u1);

        User result = userRepositoy.findByName(userName).get();

        assertEquals(result.getName(), userName);
    }

    @Test
    void findByEmail() {
        User u1 = new User();
        String userName = "user1";
        String email = "user1@gmail.com";
        u1.setName(userName);
        u1.setEmail(email);

        userRepositoy.save(u1);

        User result = userRepositoy.findByEmail(email).get();

        assertEquals(result.getEmail(), email);
    }

}
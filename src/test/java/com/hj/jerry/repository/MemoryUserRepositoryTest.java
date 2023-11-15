package com.hj.jerry.repository;

import com.hj.jerry.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryUserRepositoryTest {

    MemoryUserRepository userRepository = new MemoryUserRepository();

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void findAll() {
        User u1 = new User();
        User u2 = new User();
        u1.setName("user1");
        u2.setName("user2");

        userRepository.save(u1);
        userRepository.save(u2);

        List<User> userList = userRepository.findAll();

        boolean result = userList.containsAll(Arrays.asList(u1,u2));

        assertTrue(result);
    }

    @Test
    void save() {
        User u1 = new User();
        User u2 = new User();
        u1.setName("user1");
        u2.setName("user2");

        userRepository.save(u1);
        userRepository.save(u2);

        List<User> userList = userRepository.findAll();

        assertEquals(2, userList.size());
    }

    @Test
    void findById() {
        User u1 = new User();
        String userName = "user1";
        u1.setName(userName);

        userRepository.save(u1);

        User result = userRepository.findById(1L).get();

        assertEquals(result.getId(), 1L);
    }

    @Test
    void findByName() {
        User u1 = new User();
        String userName = "user1";
        u1.setName(userName);

        userRepository.save(u1);

        User result = userRepository.findByName(userName).get();

        assertEquals(result.getName(), userName);
    }

    @Test
    void findByEmail() {
        User u1 = new User();
        String userName = "user1";
        String email = "user1@gmail.com";
        u1.setName(userName);
        u1.setEmail(email);

        userRepository.save(u1);

        User result = userRepository.findByEmail(email).get();

        assertEquals(result.getEmail(), email);
    }

}
package com.hj.jerry.service;

import com.hj.jerry.domain.User;
import com.hj.jerry.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    UserService userService = new UserService(memoryUserRepository);

    @AfterEach
    void afterEach() {
        memoryUserRepository.clearStore();
    }

    @Test
    void join() {
        User user = new User();
        user.setName("user1");

        Long userId = userService.join(user);

        User result = userService.findOne(userId).orElseThrow();

        Assertions.assertEquals("user1", result.getName());
    }

    @Test
    void joinWithDuplicate() {
        User user = new User();
        User duplicate = new User();
        user.setEmail("user1@gmail.com");
        duplicate.setEmail("user1@gmail.com");

        userService.join(user);

        Assertions.assertThrows(IllegalStateException.class, () -> userService.join(duplicate));
    }

    @Test
    void findUsers() {
        User user1 = new User()
    }

    @Test
    void findOne() {
    }
}
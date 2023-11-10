package com.hj.jerry.service;

import com.hj.jerry.domain.User;
import com.hj.jerry.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceTest {

    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    private UserService userService = new UserService(memoryUserRepository);

    @AfterEach
    void afterEach() {
        memoryUserRepository.clearStore();
    }

    @Test
    void join() {
        User user1 = new User();
        User user2 = new User();
        user1.setEmail("test1@gmail.com");
        user2.setEmail("test2@gmail.com");

        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);


        Assertions.assertEquals(1, userId1);
        Assertions.assertEquals(2, userId2);
        Assertions.assertEquals(2, userService.findMembers().size());
    }

    @Test
    void joinWithDuplication() {
        User user1 = new User();
        User user2 = new User();
        user1.setEmail("test1@gmail.com");
        user2.setEmail("test1@gmail.com");

        userService.join(user1);
        Assertions.assertThrows(IllegalStateException.class, () -> userService.join(user2));

    }

    @Test
    void findMembers() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setEmail("test1@gmail.com");
        user2.setEmail("test2@gmail.com");
        user3.setEmail("test3@gmail.com");

        userService.join(user1);
        userService.join(user2);
        userService.join(user3);

        List<User> list = userService.findMembers();
        List<String> emailList = list.stream().map(User::getEmail).toList();
        List<String> expectedEmailList = List.of(user1.getEmail(), user2.getEmail(), user3.getEmail());

        Assertions.assertTrue(emailList.containsAll(expectedEmailList));
    }


    @Test
    void findOne() {
        User user1 = new User();
        user1.setEmail("test1@gmail.com");

        Long userId1 = userService.join(user1);

        User result = userService.findOne(userId1);

        Assertions.assertEquals(user1.getEmail(), result.getEmail());
    }
}
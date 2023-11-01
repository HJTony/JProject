package com.hj.jerry.controller;

import com.hj.jerry.VO.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @GetMapping("/user")
    public User user() {
        User user = new User("tony", "j_tony@gmail.com");

        return user;
    }
}

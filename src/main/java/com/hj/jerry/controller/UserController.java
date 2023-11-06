package com.hj.jerry.controller;

import com.hj.jerry.VO.User;
import com.hj.jerry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<User> searchUser(@RequestBody User user) {
        User responseUser = userService.searchUser(user);
        return ResponseEntity.ok(responseUser);
    }
}

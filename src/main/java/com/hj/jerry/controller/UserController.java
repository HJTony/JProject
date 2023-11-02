package com.hj.jerry.controller;

import com.hj.jerry.VO.Customer;
import com.hj.jerry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
        userService.createUser(customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Customer> searchUser(@RequestBody Customer customer) {
        Customer responseCustomer = userService.searchUser(customer);
        return ResponseEntity.ok(responseCustomer);
    }
}

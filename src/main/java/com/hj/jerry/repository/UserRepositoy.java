package com.hj.jerry.repository;

import com.hj.jerry.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoy {
    List<User> findAll();
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

}

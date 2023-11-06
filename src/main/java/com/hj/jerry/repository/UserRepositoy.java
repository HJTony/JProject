package com.hj.jerry.repository;

import com.hj.jerry.VO.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoy {
    List<User> selectAll();
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}

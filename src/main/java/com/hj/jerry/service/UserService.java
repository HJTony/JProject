package com.hj.jerry.service;

import com.hj.jerry.domain.User;
import com.hj.jerry.repository.UserRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRepositoy userRepositoy;

    public Long join(User user) {
        userRepositoy.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 회원");
                });
        userRepositoy.save(user);
        return user.getId();
    }


    public List<User> findMembers() {
        return null;
    }

    public User findOne(Long userId) {
        return userRepositoy.findById(userId).get();
    }
}

package com.hj.jerry.service;

import com.hj.jerry.domain.User;
import com.hj.jerry.repository.UserRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserRepositoy userRepositoy;

    public UserService(UserRepositoy userRepositoy) {
        this.userRepositoy = userRepositoy;
    }

    public Long join(User user) {
        validateDuplicateUser(user);
        userRepositoy.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        userRepositoy.findByEmail(user.getEmail())
                .ifPresent(m -> {throw new IllegalStateException("중복된 회원");});
    }

    public List<User> findUsers() {
        return userRepositoy.findAll();
    }

    public Optional<User> findOne(Long userId) {
        return userRepositoy.findById(userId);
    }
}

package com.hj.jerry.service;

import com.hj.jerry.domain.User;
import com.hj.jerry.repository.UserRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {


    private final UserRepositoy userRepositoy;

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
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 회원");
                });
    }

    public List<User> findMembers() {
        return userRepositoy.findAll();
    }

    public User findOne(Long userId) {
        return userRepositoy.findById(userId).get();
    }

}

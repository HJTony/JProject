package com.hj.jerry.repository;

import com.hj.jerry.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepositoy {

    private static Map<Long, User> userMap = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User save(User user) {
        user.setId(++sequence);
        userMap.put(sequence, user);
        return user;

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userMap.values().stream().filter(u -> u.getName().equals(name)).findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userMap.values().stream().filter(u -> u.getEmail().equals(email)).findAny();
    }

    public void clearStore() {
        userMap.clear();
        sequence=0;
    }
}

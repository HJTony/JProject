package com.hj.jerry.service;

import com.hj.jerry.VO.User;
import com.hj.jerry.repository.UserRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRepositoy userRepositoy;

    public Long join(User user) {
        userRepositoy.findByEmail(user.getEmail())
                .ifPresent(m -> {throw new IllegalStateException("중복된 회원");});
        userRepositoy.save(user);
        return user.getId();
    }

    public void createUser(User user) {
        jdbcTemplate.update("INSERT INTO users(first_name, last_name) VALUES (?,?)", user.getFirstName(), user.getLastName());
    }

    public User searchUser(User user) {
        String[] params = new String[]{user.getFirstName(), user.getLastName()};
        String queryStatement = "SELECT first_name, last_name FROM users WHERE first_name=? AND last_name = ?";
        RowMapper<User> rowMapper = (rs, rowNum) -> new User(0L, rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
        List<User> list = jdbcTemplate.query(queryStatement, params, rowMapper);
        return list.get(0);
    }

}

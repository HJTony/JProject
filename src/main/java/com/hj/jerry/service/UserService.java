package com.hj.jerry.service;

import com.hj.jerry.VO.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void createUser(Customer customer) {
        jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", customer.getFirstName(), customer.getLastName());
    }

    public Customer searchUser(Customer customer) {
        String[] params = new String[]{customer.getFirstName(), customer.getLastName()};
        String queryStatement = "SELECT first_name, last_name FROM customers WHERE first_name=? AND last_name = ?";
        RowMapper<Customer> rowMapper = (rs, rowNum) -> new Customer(0L, rs.getString("first_name"), rs.getString("last_name"));
        List<Customer> list = jdbcTemplate.query(queryStatement, params, rowMapper);
        return list.get(0);
    }

}

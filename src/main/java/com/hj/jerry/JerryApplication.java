package com.hj.jerry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class JerryApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(JerryApplication.class, args);
		//TODO
		/*
		유저 인증, DB
		광고 서비스
		결재 서비스

		 */
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("init");

		jdbcTemplate.execute("DROP TABLE users IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE users(" +
				"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
	}
}

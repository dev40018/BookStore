package com.dbproject.jdbcproject;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class JdbcprojectApplication implements CommandLineRunner {

	// object for interacting with database
	final private DataSource dataSource;

    public JdbcprojectApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
		SpringApplication.run(JdbcprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("dataSource: " + dataSource.toString());
		// using JdbcTemplate for querying the DB
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//checking if jdbcTemplate works
		jdbcTemplate.execute("SELECT 1");

	}
}

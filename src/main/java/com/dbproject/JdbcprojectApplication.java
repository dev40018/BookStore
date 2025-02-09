package com.dbproject;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class JdbcprojectApplication {

    public static void main(String[] args) {
		SpringApplication.run(JdbcprojectApplication.class, args);
	}

}

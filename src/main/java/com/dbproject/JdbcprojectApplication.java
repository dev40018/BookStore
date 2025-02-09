package com.dbproject;

import com.dbproject.dao.AuthorDAO;
import com.dbproject.domain.Author;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Log
public class JdbcprojectApplication implements CommandLineRunner {

	private final AuthorDAO authorDAO;

    public JdbcprojectApplication(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public static void main(String[] args) {
		SpringApplication.run(JdbcprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		authorDAO.create(new Author(32L, "josh", 32));
//		authorDAO.findMany();
		log.info("Hey There");
	}
}

package com.dbproject.dao.impl;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.AuthorDAO;
import com.dbproject.domain.Author;

public class AuthorDAOImpl implements AuthorDAO {
    
     
    private final JdbcTemplate jdbcTemplate;
    // injecting it from context which is provided in databaseConfig class
    public AuthorDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author){
        jdbcTemplate.update("INSERT INTO authors (id, name, age) VALUES(?, ?, ?)", 
        author.getId(),author.getName(), author.getAge()
        );
    }

    // it will rather returns a one Author or Empty as Optional rather than null which is type safe
    public Optional<Author> findOne(long l) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

}

package com.dbproject.dao.impl;

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

}

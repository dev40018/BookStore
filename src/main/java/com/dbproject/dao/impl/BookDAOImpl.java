package com.dbproject.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.BookDAO;

public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    

    
}

package com.dbproject.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.BookDAO;
import com.dbproject.domain.Book;

public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl( JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Books (isbn, title, author_id) VALUES(?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthorId());
    }
    public void findOne(long l) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }
    

    
}

package com.dbproject.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.BookDAO;
import com.dbproject.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl( JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO Books (isbn, title, author_id) VALUES(?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId());
    }



    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT * FROM books WHERE isbn = ? LIMIT 1",
                new BookDaoMapper(), isbn);
        return results.stream().findFirst();
    }

    public static class BookDaoMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
    

    
}

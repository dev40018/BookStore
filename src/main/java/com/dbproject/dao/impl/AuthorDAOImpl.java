package com.dbproject.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

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
    public Optional<Author> findOne(long authorId) {
        List<Author> results = jdbcTemplate.query("SELECT * FROM authors WHERE id=? LIMIT 1",new AuthorDAOMapper(), authorId);
        return results.stream().findFirst();
    }

    public static class AuthorDAOMapper implements RowMapper<Author>{

        @Override
        @Nullable
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

            // we are mapping resultSet objects to java Objects with their corresponding column names in SQL lilke rs.getLong("columName")
            return Author.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .age(rs.getInt("age"))
            .build();
        }

    }

}

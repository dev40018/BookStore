package com.dbproject.dao;

import java.util.Optional;

import com.dbproject.domain.Author;

public interface AuthorDAO {
    public void create(Author author);

    public Optional<Author> findOne(long authorId);
} 

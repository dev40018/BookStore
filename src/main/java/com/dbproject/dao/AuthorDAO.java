package com.dbproject.dao;

import java.util.List;
import java.util.Optional;

import com.dbproject.domain.Author;

public interface AuthorDAO {
    public void create(Author author);

    public Optional<Author> findOne(long authorId);

    public List<Author> findMany();
    public void update(Author author, Long id);
} 

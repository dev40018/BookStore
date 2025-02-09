package com.dbproject.dao;

import com.dbproject.domain.Book;

import java.util.Optional;

public interface BookDAO {
    public void create(Book book);
    Optional<Book> findOne(String isbn);
}

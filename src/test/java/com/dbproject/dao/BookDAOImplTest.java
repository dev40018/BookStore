package com.dbproject.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.impl.BookDAOImpl;
import com.dbproject.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDAOImplTest {

    @Mock
    private  JdbcTemplate jdbcTemplate;

    @InjectMocks
    private  BookDAOImpl underTest;



    @Test
    public void testThatCreateBookWillGenerateCorrectSQL(){
        Book book = Book.builder()
        .isbn("SO432DFS")
        .title("SomeOne")
        .authorId(1L)
        .build();

        underTest.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO Books (isbn, title, author_id) VALUES(?, ?, ?)"),
         eq("SO432DFS"), eq("SomeOne"), eq(1L)
         );
    }
    
}

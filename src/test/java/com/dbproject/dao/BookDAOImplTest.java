package com.dbproject.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
        Book book = TestDataUtil.createTestBook1();

        underTest.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO Books (isbn, title, author_id) VALUES(?, ?, ?)"),
         eq("SO432DFS"), eq("SomeOne"), eq(2L)
         );
    }

    @Test
    public void checkThatFindOneGeneratesCorrectSQL(){
        underTest.findOne("SO432DFS");
        verify(jdbcTemplate).query(eq("SELECT * FROM books WHERE isbn = ? LIMIT 1"),
        ArgumentMatchers.<BookDAOImpl.BookDaoMapper>any(),eq("SO432DFS"));
    }

    @Test
    public void testFindManyMethodGeneratesCorrectSQL(){
        underTest.findMany();
        verify(jdbcTemplate).query(eq("SELECT * FROM books"), ArgumentMatchers.<BookDAOImpl.BookDaoMapper>any());
    }





}

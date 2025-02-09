package com.dbproject.dao;

import com.dbproject.dao.impl.AuthorDAOImpl;
import com.dbproject.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * in unit tests where you want to ensure your data access layer is making the correct database calls, without actually hitting the database.
 */

//we are working on unit test, enables testRunner to use Mockito Functionality

@ExtendWith(MockitoExtension.class)
public  class AuthorDAOImplTest {


    // colabarator dps like JdbcTemplate which AuthorDAOImpl is depend on, will be mocked in here to use
    @Mock
    private JdbcTemplate jdbcTemplate;


    // before each test on instance of AuthorDAOImpl is created for us
    @InjectMocks
    private AuthorDAOImpl underTest;


    @Test
    public void testThatCreateAuthorIsResultingCorrectSQL(){

        // first we create a atuhor for test
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        // we want to assert on the SQL that is generated and because it doesn't return anything we use Mockit Verify

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES(?, ?, ?)"),
                eq(1L), eq("Jason"), eq(32)
        );
        /*
         * verify(jdbcTemplate) - This is a Mockito method that verifies that a specific interaction
         *  happened with the mocked jdbcTemplate object.
         *  It's checking if a particular method was called on the jdbcTemplate with specific arguments.
         * .update() - This is a JdbcTemplate method that's being verified.
         * It's typically used to perform INSERT, UPDATE, or DELETE operations in the database.
         * ArgumentMatchers.eq() and eq() - These are Mockito matchers that verify exact equality of arguments.
         *  They ensure that the exact values were passed to the update method.
         */

    }

    @Test
    public void testThatFindOneAuthorGeneratesCorrectSQL(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT * FROM authors WHERE id=? LIMIT 1"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorDAOMapper>any(),
                eq(1L));
        //NOTE: because we are using JDBC/DAO pattern we need to handle java Object <-> SQL conversion with ourselves and one of those features that enables us to do that is rowMapper
        // rowmapper is a generic which needs a type
    }

    @Test
    public void testFindManyMethodGeneratesCorrectSQL(){
        underTest.findMany();
        verify(jdbcTemplate).query(eq("SELECT * FROM authors"), ArgumentMatchers.<AuthorDAOImpl.AuthorDAOMapper>any());
    }
    @Test
    public void testUpdateMethodGeneratesCorrectSQL(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(author, 3L);
        verify(jdbcTemplate).update("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"
                ,1L, "Jason", 32, 3L );
    }


}

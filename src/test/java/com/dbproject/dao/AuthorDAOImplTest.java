package com.dbproject.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.impl.AuthorDAOImpl;
import com.dbproject.domain.Author;
/**
 * in unit tests where you want to ensure your data access layer is making the correct database calls, without actually hitting the database.
 */

//we are working on unit test, enables testRunner to use Mockito Functionality
@ExtendWith(MockitoExtension.class)
public class AuthorDAOImplTest {


    // colabarator dps like JdbcTemplate which AuthorDAOImpl is depend on, will be mocked in here to use
    @Mock
    private  JdbcTemplate jdbcTemplate;


    // before each test on instance of AuthorDAOImpl is created for us
    @InjectMocks
    private  AuthorDAOImpl underTest;


    @Test
    public void testThatCreateAuthorIsResultingCorrectSQL(){

        // first we create a atuhor for test
        Author author = Author.builder()
        .id(1L)
        .name("Jason")
        .age(32)
        .build();

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
         * .update() - This is a JdbcTemplate method that's being verified. It's typically used to perform INSERT, UPDATE, or DELETE operations in the database.
         * ArgumentMatchers.eq() and eq() - These are Mockito matchers that verify exact equality of arguments. They ensure that the exact values were passed to the update method.
         */

    }
    
}
/**
 * @Mock:

Creates a mock (dummy) implementation of a class or interface
The mock by default returns null for objects, 0 for numbers, and false for booleans
You can define custom behavior using when() and thenReturn()

 * @InjectMocks:

Creates an instance of the class and automatically injects the mocks marked with @Mock into it
Used for the class you're actually testing
Tries to inject mocks via constructor injection first, then property setter injection, and finally field injection

Key things to remember:

Use @Mock for dependencies
Use @InjectMocks for the class you're testing
Both annotations need @ExtendWith(MockitoExtension.class) at the class level to work
Order doesn't matter - Mockito will figure out which mocks to inject where

The key reasons we don't mock the class under test:

We want to test the actual business logic implementation
We want to verify error handling works correctly
We want to ensure the class integrates correctly with its dependencies
We want to catch actual bugs in our implementation
We want to verify the real behavior, not just mock responses


Key reasons for using mocks:

Speed - Tests run much faster without real databases/services
Reliability - Tests aren't affected by external systems
Control - We can simulate edge cases and errors
Independence - Tests don't require external setup
Cost - No need for external service accounts or resources
Consistency - Tests behave the same way every time
Focus - We test only the logic we care about
Parallel Execution - Tests can run simultaneously without conflicts

Integration tests are where we test with real dependencies, but that's a different type of testing with different goals.
 */
package com.dbproject.dao;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dbproject.dao.impl.AuthorDAOImpl;

//we are working on unit test, enables testRunner to use Mockito Functionality
@ExtendWith(MockitoExtension.class)
public class AuthorDAOImplTest {


    // colabarator dps like JdbcTemplate which AuthorDAOImpl is depend on, will be mocked in here to use
    @Mock
    private final JdbcTemplate jdbcTemplate;


    // before each test on instance of AuthorDAOImpl is created for us
    @InjectMocks
    private final AuthorDAOImpl underTest;

    public AuthorDAOImplTest(AuthorDAOImpl underTest, JdbcTemplate jdbcTemplate){
        this.underTest = underTest;
        this.jdbcTemplate = jdbcTemplate;
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
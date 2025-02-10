package com.dbproject.dao;


import com.dbproject.dao.impl.BookDAOImpl;
import com.dbproject.domain.Author;
import com.dbproject.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOIntegrationTest {

    private final BookDAOImpl underTest;
    private final AuthorDAO authorDAO;

    @Autowired
    public BookDAOIntegrationTest(BookDAOImpl underTest, AuthorDAO authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    void testCreateInsertsBooksInDatabaseAndFindOneWorks(){
        Book book = TestDataUtil.createTestBook();
        // since we have FK Constrain we need to have Author PK in our DB to check it
        Author author = TestDataUtil.createTestAuthor();
        authorDAO.create(author);
//        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> book1 = underTest.findOne(book.getIsbn());

        assertThat(book1).isPresent();
        assertThat(book1.get()).isEqualTo(book);


    }
    @Test
    void testCreateInsertsBooksInDatabaseAndFindManyWorks(){
        Book book = TestDataUtil.createTestBook();
        Book book1 = TestDataUtil.createTestBook1();
        Book book2 = TestDataUtil.createTestBook2();
        // since we have FK Constrain we need to have Author PK in our DB to check it
        Author author = TestDataUtil.createTestAuthor();
        Author author1 = TestDataUtil.createTestAuthor1();
        Author author2 = TestDataUtil.createTestAuthor2();
        authorDAO.create(author);
        authorDAO.create(author1);
        authorDAO.create(author2);
        //book.setAuthorId(author.getId()); is optional
        book.setAuthorId(author.getId());
        book1.setAuthorId(author1.getId());
        book2.setAuthorId(author2.getId());
        underTest.create(book);
        underTest.create(book1);
        underTest.create(book2);
        List<Book> books = underTest.findMany();

        assertThat(books).hasSize(3).containsExactly(book, book1, book2);
        // assertThat(books).containsExactly(book, book1, book2);


    }
    @Test
    void testUpdatesBooksInDatabaseAndFindManyWorks(){
        Author author = TestDataUtil.createTestAuthor();
        authorDAO.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        book.setTitle("UPDATE");
        underTest.update(book, book.getIsbn());

        Optional<Book> book1 = underTest.findOne(book.getIsbn());

        assertThat(book1).isPresent();
        assertThat(book1.get()).isEqualTo(book);


    }
}
/**
 *
 *
 * @SpringBootTest is like setting up a mini version of your entire Spring application for testing.
 * When you add this annotation to a test class, Spring Boot creates a
 * complete application context - imagine it as starting up a smaller version of your application
 * with all its components (beans, configurations, etc.) ready for testing.
 * This is particularly valuable when you need to test how different parts of your application work together.
 *
 * Here's a typical example of how you might use @SpringBootTest:
 *
 * ```java
 * @SpringBootTest
 * public class UserServiceIntegrationTest {
 *     @Autowired
 *     private UserService userService;
 *
 *     @Test
 *     void whenCreateUser_thenUserIsSavedInDatabase() {
 *         // Test uses actual application context
 *         User user = userService.createUser("testUser");
 *         assertNotNull(user.getId());
 *     }
 * }
 * ```
 *
 * @ExtendWith(SpringExtension.class) serves as a bridge between Spring and JUnit 5 (Jupiter).
 * Think of it as a translator that helps Spring and JUnit 5 communicate with each other.
 * This extension tells JUnit 5 how to create and inject Spring beans into your test classes,
 * manage the application context, and handle Spring-specific features during testing.
 *
 * These annotations often work together because they serve complementary purposes.
 * Here's a more detailed example showing how they combine:
 *
 * ```java
 * @SpringBootTest
 * @ExtendWith(SpringExtension.class)
 * public class CompleteIntegrationTest {
 *     @Autowired
 *     private UserService userService;
 *
 *     @Autowired
 *     private UserRepository userRepository;
 *
 *     @Test
 *     void whenUserCreated_thenEmailServiceNotified() {
 *         // This test can use the full Spring context
 *         // Including database connections, email services, etc.
 *         User user = userService.createUser("test@example.com");
 *
 *         // We can verify interactions across multiple components
 *         verify(emailService).sendWelcomeEmail(user.getEmail());
 *     }
 * }
 * ```
 *
 * It's worth noting that in recent versions of Spring Boot (2.1.0 and later),
 * @SpringBootTest already includes @ExtendWith(SpringExtension.class),
 * so you don't actually need to specify both. The following is sufficient:
 *
 * ```java
 * @SpringBootTest
 * public class ModernSpringBootTest {
 *     // Test methods here
 * }
 * ```
 *
 * You might be wondering when to use these annotations versus simpler testing approaches.
 * Here's the key consideration: use @SpringBootTest when you need to test
 * the integration of multiple components together. For simpler unit tests where
 * you just want to test a single component in isolation, you might prefer
 * @WebMvcTest (for controllers) or @DataJpaTest (for repositories),
 * which load only the necessary parts of the application context.
 *
 * For example, here's how you might write a more focused test:
 *
 * ```java
 * @WebMvcTest(UserController.class)  // Loads only web layer
 * public class UserControllerTest {
 *     @Autowired
 *     private MockMvc mockMvc;
 *
 *     @MockBean
 *     private UserService userService;  // Mock the service
 *
 *     @Test
 *     void whenGetUser_thenReturnsUser() throws Exception {
 *         // This test focuses only on the web layer
 *         mockMvc.perform(get("/users/1"))
 *                .andExpect(status().isOk());
 *     }
 * }
 * ```
 *o
 */

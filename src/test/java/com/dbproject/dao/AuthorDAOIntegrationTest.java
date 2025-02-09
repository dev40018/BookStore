package com.dbproject.dao;


import com.dbproject.dao.impl.AuthorDAOImpl;
import com.dbproject.domain.Author;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // for each test ApplicationContext will be freed
public class AuthorDAOIntegrationTest {
    private final AuthorDAOImpl underTest;

    @Autowired
    public AuthorDAOIntegrationTest(AuthorDAOImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    void testCreateInsertsAuthorsInDatabaseAndFindOneWorks(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> author1 = underTest.findOne(author.getId());

        assertThat(author1).isPresent(); // checks if author1 has found from db
        assertThat(author1.get()).isEqualTo(author);
    }
    @Test
    void testCreateInsertsAuthorsInDatabaseAndFindManyWorks(){
        Author author = TestDataUtil.createTestAuthor();
        Author author1 = TestDataUtil.createTestAuthor1();
        Author author2 = TestDataUtil.createTestAuthor2();
        underTest.create(author);
        underTest.create(author1);
        underTest.create(author2);
        List<Author> authors = underTest.findMany();

        assertThat(authors).hasSize(3).containsExactly(author, author1, author2);
    }
}

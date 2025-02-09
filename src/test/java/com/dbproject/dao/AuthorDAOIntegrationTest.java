package com.dbproject.dao;


import com.dbproject.dao.impl.AuthorDAOImpl;
import com.dbproject.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDAOIntegrationTest {
    private final AuthorDAOImpl underTest;

    @Autowired
    public AuthorDAOIntegrationTest(AuthorDAOImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    void testCreateInsertsValuesInDBandFindOneWorks(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> author1 = underTest.findOne(author.getId());

        assertThat(author1).isPresent(); // checks if author1 has found from db
        assertThat(author1.get()).isEqualTo(author);
    }
}

package com.dbproject.dao;

import com.dbproject.domain.Author;
import com.dbproject.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public final class TestDataUtil {
    private TestDataUtil(){};

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Jason")
                .age(32)
                .build();
    }
    public static Author createTestAuthor1() {
        return Author.builder()
                .id(2L)
                .name("Josh")
                .age(21)
                .build();
    }
    public static Author createTestAuthor2() {
        return Author.builder()
                .id(3L)
                .name("Frank")
                .age(62)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("KL#$#8978")
                .title("ANotherONe")
                .authorId(1L)
                .build();
    }
    public static Book createTestBook1() {
        return Book.builder()
                .isbn("SO432DFS")
                .title("SomeOne")
                .authorId(2L)
                .build();
    }
    public static Book createTestBook2() {
        return Book.builder()
                .isbn("423*(SD%6")
                .title("SISO")
                .authorId(3L)
                .build();
    }
}

package com.dbproject.dao;

import com.dbproject.domain.Author;
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

}

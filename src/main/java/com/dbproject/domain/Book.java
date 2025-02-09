package com.dbproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    
    private String isbn;
    private String title;
    // database does need numeric value instead of Author as an object
    // mapping happens inside the database
    private Long authorId;

}

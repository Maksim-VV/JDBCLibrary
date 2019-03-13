package com.vasiliska.JDBCLibrary.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"id", "authorId", "genreId"}, includeFieldNames=false)
@Builder
public class Book {
    int id;
    String name;
    int genreId;
    int authorId;
    String authorName;
    String genreName;
}

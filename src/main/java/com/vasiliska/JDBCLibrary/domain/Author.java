package com.vasiliska.JDBCLibrary.domain;

import lombok.Data;

@Data
public class Author {
    private final long authorId;
    private final String authorName;

    public Author(long authorId, String authorName)
    {
        this.authorId = authorId;
        this.authorName = authorName;
    }
}

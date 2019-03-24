package com.vasiliska.JDBCLibrary.domain;

import lombok.Data;

@Data
public class Book {
    private long id;
    private String name;
    private Genre genre;
    private Author author;

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.genre = genre;
        this.author = author;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return name + " " + author.getAuthorName() + " " + genre.getGenreName();
    }

}

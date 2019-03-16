package com.vasiliska.JDBCLibrary.domain;

import lombok.Data;

@Data
public class Book {
    long id;
    String name;

    Genre genre;
    Author author;

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

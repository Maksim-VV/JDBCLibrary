package com.vasiliska.JDBCLibrary.dao;

import com.vasiliska.JDBCLibrary.domain.Book;

import java.util.List;


public interface BookDao {

    List<Book> getAllBooks();
    List<Book> getBookByAuthor(String name);
    List<Book> getBookByGenre(String name);
    
    Book getBookByName(String name);
    void insertBook(Book book);
    boolean delBook(String name);
    boolean delAuthor(int authorId);
    boolean delGenre(int genreId);

    int getIdAuthor(String name);
    void insertAuthor(String name);

    int getIdGenre(String name);
    void insertGenre(String name);

    int getCountBookByAuthor(int authorId);
    int getCountBookByGenre(int genreId);
}

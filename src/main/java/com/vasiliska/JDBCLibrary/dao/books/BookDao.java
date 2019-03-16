package com.vasiliska.JDBCLibrary.dao.books;

import com.vasiliska.JDBCLibrary.domain.Book;

import java.util.List;


public interface BookDao {

    List<Book> getAllBooks();

    List<Book> getBookByAuthor(String name);

    List<Book> getBookByGenre(String name);

    Book getBookByName(String name);

    void insertBook(Book book);

    boolean delBook(String name);

    int getCountBookByAuthor(long authorId);

    int getCountBookByGenre(long genreId);
}

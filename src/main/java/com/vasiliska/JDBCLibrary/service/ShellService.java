package com.vasiliska.JDBCLibrary.service;

public interface ShellService {

    String addBook(String bookName, String author, String genre);
    String delBook(String bookName);
    String bookByName(String bookName);
    String bookByGenre(String genre);
    String bookByAuthor(String author);
    String showAllBooks();
}

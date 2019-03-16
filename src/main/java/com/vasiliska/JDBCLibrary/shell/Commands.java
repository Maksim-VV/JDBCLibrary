package com.vasiliska.JDBCLibrary.shell;

import com.vasiliska.JDBCLibrary.service.ShellService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class Commands {

    private final ShellService shellService;

    @Autowired
    public Commands(ShellService shellService) {
        this.shellService = shellService;
    }

    @ShellMethod("Find book by name")
    public String findbook(@ShellOption String bookName) {
        return shellService.bookByName(bookName);
    }

    @ShellMethod("Add new book")
    public String addbook(@ShellOption String bookName, String author, String genre) {
        return shellService.addBook(bookName, author, genre);
    }

    @ShellMethod("Delete book")
    public String delbook(@ShellOption String bookName) {
        return shellService.delBook(bookName);
    }

    @ShellMethod("Show all books")
    public String allbooks() {
        return shellService.showAllBooks();
    }

    @ShellMethod("Show all books by author")
    public String byauthor(String authorName) {
        return shellService.bookByAuthor(authorName);
    }

    @ShellMethod("Show all books by genre")
    public String bygenre(String genreName) {
        return shellService.bookByGenre(genreName);
    }

}

package com.vasiliska.JDBCLibrary.service;

import com.vasiliska.JDBCLibrary.dao.BookDao;
import com.vasiliska.JDBCLibrary.domain.Book;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShellServiceImpl implements ShellService {

    BookDao dao;

    @Autowired
    public ShellServiceImpl(BookDao dao) {
        this.dao = dao;
    }



    @Override
    public String addBook(String bookName, String author, String genre) {
        if (!addNewBook(bookName, author, genre)) {
            return "Не удалось добавить книгу " + bookName;
        }
        return "Книга " + bookName + " добавлена";
    }

    @Override
    public String delBook(String nameBook) {
        if (!deleteBook(nameBook)) {
            return "Не удалось удалить книгу " + nameBook;
        }
        return "Книга " + nameBook + " удалена";
    }

    @Override
    public String bookByName(String bookName) {
        Book book = searchBook(bookName);
        if (book.getName().isEmpty()) {
            return "Нет такой книги!";
        }
        return book.toString();
    }


    @Override
    public String bookByAuthor(String author) {
        StringBuffer stringBuffer = new StringBuffer();
        List<Book> listBook = dao.getBookByAuthor(author);
        if (listBook == null || listBook.isEmpty()) {
            return null;
        }
        listBook.forEach(v -> {
            stringBuffer.append(v.toString() + "\n");
        });
        return stringBuffer.toString();
    }

    @Override
    public String bookByGenre(String genre) {
        StringBuffer stringBuffer = new StringBuffer();
        List<Book> listBook = dao.getBookByGenre(genre);
        if (listBook == null || listBook.isEmpty()) {
            return null;
        }
        listBook.forEach(v -> {
            stringBuffer.append(v.toString() + "\n");
        });
        return stringBuffer.toString();
    }

    @Override
    public String showAllBooks() {
        StringBuffer stringBuffer = new StringBuffer();
        List<Book> listBook = dao.getAllBooks();
        listBook.forEach(v -> {
            stringBuffer.append(v.toString() + "\n");
        });
        return stringBuffer.toString();
    }


    public boolean addNewBook(String name, String author, String genre) {
        Book book = Book.builder().name(name).authorId(getIdAuthor(author)).genreId(getIdGenre(genre)).build();
        dao.insertBook(book);
        return true;
    }

    public boolean deleteBook(String name) {
        Book book = dao.getBookByName(name);
        if (book == null || book.getName().isEmpty()) {
            System.out.println("Нет такой книги!");
            return false;
        }
        val countAuthor = dao.getCountBookByAuthor(book.getAuthorId());
        if (countAuthor == 1) {
            if (!dao.delAuthor(book.getAuthorId())) {
                log.info("Сan't find the author's id");
            }
        }
        val countGenre = dao.getCountBookByGenre(book.getGenreId());
        if (countGenre == 1) {
            if (!dao.delGenre(book.getGenreId())) {
                log.info("Сan't find the genre's id");
            }
        }
        if (!dao.delBook(name)) {
            log.info("Book " + name + " cannot be deleted!!!");
        }
        log.info("Book " + name + " has been removed!");
        return true;
    }

    public Book searchBook(String name) {
        Book book = dao.getBookByName(name);
        return book;
    }


    public int getIdAuthor(String name) {
        int idAuthor = dao.getIdAuthor(name);
        return idAuthor;
    }

    public int getIdGenre(String name) {
        int idGenre = dao.getIdGenre(name);
        return idGenre;
    }


}



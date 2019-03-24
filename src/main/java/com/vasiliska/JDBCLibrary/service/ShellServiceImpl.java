package com.vasiliska.JDBCLibrary.service;

import com.vasiliska.JDBCLibrary.dao.authors.AuthorDao;
import com.vasiliska.JDBCLibrary.dao.books.BookDao;
import com.vasiliska.JDBCLibrary.dao.genres.GenreDao;
import com.vasiliska.JDBCLibrary.domain.Author;
import com.vasiliska.JDBCLibrary.domain.Book;
import com.vasiliska.JDBCLibrary.domain.Genre;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShellServiceImpl implements ShellService {

    BookDao dao;
    GenreDao genreDao;
    AuthorDao authorDao;

    @Autowired
    public ShellServiceImpl(BookDao dao, GenreDao genreDao, AuthorDao authorDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.dao = dao;
    }

    public ShellServiceImpl() {

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


    public boolean addNewBook(String name, String authorName, String genreName) {
        val author = new Author(getIdAuthor(authorName), authorName);
        val genre = new Genre(getIdGenre(genreName), genreName);
        Book book = new Book(name, author, genre);

        dao.insertBook(book);
        return true;
    }

    public boolean deleteBook(String name) {
        Book book = dao.getBookByName(name);

        if (book == null || book.getName().isEmpty()) {
            System.out.println("Нет такой книги!");
            return false;
        }
        val countAuthor = dao.getCountBookByAuthor(book.getAuthor().getAuthorId());
        val countGenre = dao.getCountBookByGenre(book.getGenre().getGenreId());

        if (!dao.delBook(name))
        {
            log.info("Book " + name + " cannot be deleted!!!");
            return false;
        }
        else 
        {
            log.info("Book " + name + " has been removed!");
            if (countAuthor == 1)
            {
                if (!authorDao.delAuthor(book.getAuthor().getAuthorId())) {
                    log.info("Сan't find the author's id");
                }
            }

            if (countGenre == 1)
            {
                if (!genreDao.delGenre(book.getGenre().getGenreId())) {
                    log.info("Сan't find the genre's id");
                }
            }
       }
        return true;
    }

    public Book searchBook(String name) {
        Book book = dao.getBookByName(name);
        return book;
    }

    public long getIdAuthor(String name) {
        long idAuthor = authorDao.getIdAuthor(name);
        return idAuthor;
    }

    public long getIdGenre(String name) {
        long idGenre = genreDao.getIdGenre(name);
        return idGenre;
    }

}



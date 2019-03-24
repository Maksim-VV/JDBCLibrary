package com.vasiliska.JDBCLibrary.domain;

import com.vasiliska.JDBCLibrary.dao.authors.AuthorDao;
import com.vasiliska.JDBCLibrary.dao.authors.AuthorDaoJdbc;
import com.vasiliska.JDBCLibrary.dao.books.BookDaoJdbc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class})
public class BookTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @Autowired
    private AuthorDao authorDao;
    private final String TEST_BOOK_NAME = "Преступление и наказание";

    @Test
    public void testContext() {
        assertNotNull(bookDao);
    }

    @Test
    public void getAllBooks() {
        assertEquals(bookDao.getAllBooks().size(), 1);
        assertEquals(bookDao.getAllBooks().get(0).getName(), TEST_BOOK_NAME);
        assertEquals(bookDao.getAllBooks().get(0).getAuthor().getAuthorName(), "Достоевский");
        assertEquals(bookDao.getAllBooks().get(0).getGenre().getGenreName(), "Драма");
    }

   @Test
    public void insertBookTest() {
        String newNameBook = "Му-му";
        Author author = new Author(2, "Тургенев");
        authorDao.insertAuthor("Тургенев");
        Genre genre = new Genre(1, "Драма");
        Book book = new Book(newNameBook, author, genre);
        bookDao.insertBook(book);
        assertEquals(bookDao.getAllBooks().size(), 2);
        assertEquals(bookDao.getAllBooks().get(1).getName(), newNameBook);
    }

   @Test
    public void getIdName() {
       assertEquals(bookDao.getBookByName(TEST_BOOK_NAME).getId(), 1);
    }

    @Test
    public void getGenre() {
        assertEquals(bookDao.getBookByGenre("Драма").get(0).getName(), TEST_BOOK_NAME);
    }

    @Test
    public void getAuthor() {
        assertEquals(bookDao.getBookByAuthor("Достоевский").get(0).getName(),TEST_BOOK_NAME);
    }

    @Test
    public void delBookTest() {
        assertFalse(bookDao.delBook("Собачье сердце"));
        assertTrue(bookDao.delBook(TEST_BOOK_NAME));
    }

}
package com.vasiliska.JDBCLibrary.dao;

import com.vasiliska.JDBCLibrary.dao.authors.AuthorDaoJdbc;
import com.vasiliska.JDBCLibrary.dao.books.BookDaoJdbc;
import com.vasiliska.JDBCLibrary.dao.genres.GenreDaoJdbc;
import com.vasiliska.JDBCLibrary.domain.Author;
import com.vasiliska.JDBCLibrary.domain.Book;
import com.vasiliska.JDBCLibrary.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@TestPropertySource(properties = {"spring.datasource.schema=classpath:testschema.sql"})
public class daoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @Autowired
    private AuthorDaoJdbc authorDao;
    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    public void testContext() {
        assertNotNull(bookDao);
    }

    @Test
    public void getAllBooks() {
        assertEquals(bookDao.getAllBooks().size(), 1);
        assertEquals(bookDao.getAllBooks().get(0).getName(), "Преступление и наказание");
        assertEquals(bookDao.getAllBooks().get(0).getAuthor().getAuthorName(), "Достоевский");
        assertEquals(bookDao.getAllBooks().get(0).getGenre().getGenreName(), "Драма");
    }

    @Test
    public void insertBookTest() {
        String newNameBook =  "Му-му";
        Author author = new Author(2, "Тургенев");
        authorDao.insertAuthor("Тургенев");
        Genre genre = new Genre(1, "Драма");
        Book book = new Book(newNameBook, author, genre);
        bookDao.insertBook(book);
        assertEquals(bookDao.getAllBooks().size(), 2);
        assertEquals(bookDao.getAllBooks().get(1).getName(), newNameBook);
    }

    @Test
    public void getCountBookByAuthor() {
        assertEquals(bookDao.getCountBookByAuthor(1), 1);
        assertEquals(bookDao.getCountBookByAuthor(3), 0);
    }

    @Test
    public void getCountBookByGenre() {
        assertEquals(bookDao.getCountBookByGenre(1), 1);
        assertEquals(bookDao.getCountBookByGenre(2), 0);
    }
}
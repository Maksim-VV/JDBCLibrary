package com.vasiliska.JDBCLibrary.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootApplication
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;

    @Test
    public void testContext() {
        assertNotNull(dao);
    }

    @Test
    public void getAllBooks() {
        assertEquals(dao.getAllBooks().size(), 1);
        assertEquals(dao.getAllBooks().get(0).getName(), "Му-му");
        assertEquals(dao.getAllBooks().get(0).getAuthorName(), "Тургенев");
        assertEquals(dao.getAllBooks().get(0).getGenreName(), "Драма");
    }

    @Test
    public void getBookByName() {
        assertEquals(dao.getBookByName("Му-му").getId(), 1);
    }

    @Test
    public void getIdAuthor() {
        assertEquals(dao.getIdAuthor("Тургенев"), 1);
        assertEquals(dao.getIdAuthor("Пушкин"), 2);
    }

    @Test
    public void getIdGenre() {
        assertEquals(dao.getIdGenre("Драма"), 1);
        assertEquals(dao.getIdGenre("Комедия"), 2);
    }

    @Test
    public void getCountBookByAuthor() {
        assertEquals(dao.getCountBookByAuthor(1), 1);
        assertEquals(dao.getCountBookByAuthor(3), 0);
    }

    @Test
    public void getCountBookByGenre() {
        assertEquals(dao.getCountBookByGenre(1), 1);
        assertEquals(dao.getCountBookByGenre(2), 0);
    }
}
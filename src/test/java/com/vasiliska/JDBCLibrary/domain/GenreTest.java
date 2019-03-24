package com.vasiliska.JDBCLibrary.domain;

import com.vasiliska.JDBCLibrary.dao.genres.GenreDaoJdbc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({GenreDaoJdbc.class})
public class GenreTest {

    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    public void getGenreName() {
       assertEquals(genreDao.getIdGenre("Преступление и наказание"), 2);
    }

    @Test
    public void insertTest() {
        assertFalse(genreDao.delGenre(2));
    }
}
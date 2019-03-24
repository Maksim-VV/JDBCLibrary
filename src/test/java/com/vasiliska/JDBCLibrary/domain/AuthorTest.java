package com.vasiliska.JDBCLibrary.domain;

import com.vasiliska.JDBCLibrary.dao.authors.AuthorDaoJdbc;
import org.h2.tools.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@JdbcTest
@Import({AuthorDaoJdbc.class})
public class AuthorTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Test
    public void getAuthorName() throws SQLException {
        assertEquals(authorDao.getIdAuthor("Достоевский"), 1);
    }

    @Test
    public void insertTest() {
        assertFalse(authorDao.delAuthor(2));
    }
}
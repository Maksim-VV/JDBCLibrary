package com.vasiliska.JDBCLibrary.service;

import com.vasiliska.JDBCLibrary.dao.books.BookDaoJdbc;
import com.vasiliska.JDBCLibrary.shell.Commands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class ShellCommandTest{


   @Autowired
    private Shell shell;

    @Autowired
    ShellServiceImpl shellService;

   @Mock
    private BookDaoJdbc dao;

    @Autowired
    Commands commands;

    private final String TEST_BOOK_NAME = "Преступление и наказание";
    private final String TEST_BOOK_AUTHOR = "Достоевский";
    private final String TEST_BOOK_GENRE = "Драма";

    @Test
    public void showAllBooks() {
        Object textTest = shell.evaluate(() -> "allbooks");
        assertNotNull(textTest);
        assertTrue(textTest.toString().contains(TEST_BOOK_NAME));
        assertTrue(textTest.toString().contains(TEST_BOOK_AUTHOR));
        assertTrue(textTest.toString().contains(TEST_BOOK_GENRE));
    }

   @Test
    public void bookByAuthor() {
        Object textTest = shell.evaluate(() -> "byauthor Достоевский");
        assertTrue(textTest.toString().contains(TEST_BOOK_NAME));
    }

    @Test
    public void bookByGenre() {
        Object textTest = shell.evaluate(() -> "bygenre Драма");
        assertTrue(textTest.toString().contains(TEST_BOOK_NAME));
    }

    @Test
    public void searchBook() {
        Object textTest = shell.evaluate(() -> "findbook Теремок");
        assertFalse(textTest.toString().contains(TEST_BOOK_AUTHOR));
    }

}
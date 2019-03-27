package com.vasiliska.JDBCLibrary.service;

import com.vasiliska.JDBCLibrary.shell.Commands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
})

@TestPropertySource(locations = "classpath:test.properties")
public class ShellCommandTest {

    @Autowired
    private Shell shell;

    @Autowired
    Commands commands;

    private final String TEST_BOOK_NAME = "Продавец воздуха";
    private final String TEST_BOOK_AUTHOR = "Беляев";
    private final String TEST_BOOK_GENRE = "Фантастика";


    public void showBooks() {
        Object textTest = shell.evaluate(() -> "allbooks");
        System.err.println(textTest.toString());
    }

    @Test
    public void showAllBooks() {
        Object textTest = shell.evaluate(() -> "allbooks");
        showBooks();

        assertTrue(textTest.toString().contains(TEST_BOOK_NAME));
        assertTrue(textTest.toString().contains(TEST_BOOK_AUTHOR));
        assertTrue(textTest.toString().contains(TEST_BOOK_GENRE));
    }

    @Test
    public void bookByAuthor() {
        Object textTest = shell.evaluate(() -> "byauthor Беляев");
        assertTrue(textTest.toString().contains(TEST_BOOK_NAME));
    }

    @Test
    public void bookByGenre() {
        Object textTest = shell.evaluate(() -> "bygenre Фантастика");
        assertTrue(textTest.toString().contains(TEST_BOOK_NAME));
    }

    @Test
    public void searchBook() {
        Object textTest = shell.evaluate(() -> "findbook Теремок");
        assertFalse(textTest.toString().contains(TEST_BOOK_AUTHOR));
    }

}
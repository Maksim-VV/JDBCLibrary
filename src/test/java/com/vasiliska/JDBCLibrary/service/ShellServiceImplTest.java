package com.vasiliska.JDBCLibrary.service;

import com.vasiliska.JDBCLibrary.dao.BookDaoJdbc;
import com.vasiliska.JDBCLibrary.shell.Commands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = com.vasiliska.JDBCLibrary.dao.TestConfig.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class ShellServiceImplTest {

    @Autowired
    ShellServiceImpl shellService;

    @Autowired
    private Shell shell;

    @Autowired
    private BookDaoJdbc dao;

    @Autowired
    Commands commands;

    @Test
    public void showAllBooks() {
        Object textTest = shell.evaluate(() -> "allbooks");
        assertTrue(textTest.toString().contains("Тургенев"));
        assertTrue(textTest.toString().contains("Му-му"));
        assertTrue(textTest.toString().contains("Драма"));
    }

    @Test
    public void bookByAuthor() {
        Object textTest = shell.evaluate(() -> "byauthor Тургенев");
        assertTrue(textTest.toString().contains("Му-му"));
    }

    @Test
    public void bookByGenre() {
        Object textTest = shell.evaluate(() -> "bygenre Драма");
        assertTrue(textTest.toString().contains("Му-му"));
    }

    @Test
    public void searchBook() {
        Object textTest = shell.evaluate(() -> "findbook Му-му");
        assertTrue(textTest.toString().contains("Тургенев"));
    }

}
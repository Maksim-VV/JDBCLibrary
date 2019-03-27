package com.vasiliska.JDBCLibrary.service;

import com.vasiliska.JDBCLibrary.dao.authors.AuthorDao;
import com.vasiliska.JDBCLibrary.dao.books.BookDao;
import com.vasiliska.JDBCLibrary.dao.genres.GenreDao;
import com.vasiliska.JDBCLibrary.domain.Author;
import com.vasiliska.JDBCLibrary.domain.Book;
import com.vasiliska.JDBCLibrary.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
})
@TestPropertySource(locations = "classpath:test.properties")
public class ShellServiceImplTest {

    @MockBean
    private BookDao bookDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    ShellServiceImpl shellService;

    private final String TEST_BOOK_NAME = "Айвенго";
    private final String TEST_AUTHOR = "В.Скотт";
    private final String TEST_GENRE = "Роман";


    @Test
    public void addBook() {
        Book book = new Book(TEST_BOOK_NAME, setAuthor(), setGenre());
        assertTrue(shellService.addNewBook(TEST_BOOK_NAME, TEST_AUTHOR, TEST_GENRE));
    }

    private Author setAuthor() {
        return new Author(1, TEST_AUTHOR);
    }

    private Genre setGenre() {
        return new Genre(1, TEST_GENRE);
    }


    @Test
    public void delBook() {
        shellService.delBook(TEST_BOOK_NAME);
        verify(bookDao).getBookByName(TEST_BOOK_NAME);
    }

    @Test
    public void bookByName() {
        shellService.bookByName(TEST_BOOK_NAME);
        verify(bookDao).getBookByName(TEST_BOOK_NAME);
    }

    @Test
    public void bookByAuthor() {
        shellService.bookByAuthor(TEST_AUTHOR);
        verify(bookDao).getBookByAuthor(TEST_AUTHOR);
    }

    @Test
    public void bookByGenre() {
        shellService.bookByGenre(TEST_GENRE);
        verify(bookDao).getBookByGenre(TEST_GENRE);
    }

    @Test
    public void showAllBooks() {
        shellService.showAllBooks();
        verify(bookDao).getAllBooks();
    }
}
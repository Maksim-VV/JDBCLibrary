package com.vasiliska.JDBCLibrary.dao;


import com.vasiliska.JDBCLibrary.dao.authors.AuthorDao;
import com.vasiliska.JDBCLibrary.dao.books.BookDaoJdbc;
import com.vasiliska.JDBCLibrary.dao.genres.GenreDao;
import com.vasiliska.JDBCLibrary.service.ShellService;
import com.vasiliska.JDBCLibrary.service.ShellServiceImpl;
import com.vasiliska.JDBCLibrary.shell.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Autowired
    private BookDaoJdbc bookDao;
    GenreDao genreDao;
    AuthorDao authorDao;


    @Bean
    public ShellServiceImpl shellServiceImpl() {
        return new ShellServiceImpl(bookDao, genreDao, authorDao);
    }

    @Bean
    ShellService shellService() {
        return new ShellServiceImpl(bookDao, genreDao, authorDao);
    }

    @Bean
    public Commands commands() {
        return new Commands(shellService());
    }
}

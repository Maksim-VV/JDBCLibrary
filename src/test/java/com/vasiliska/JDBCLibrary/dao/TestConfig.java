package com.vasiliska.JDBCLibrary.dao;

import com.vasiliska.JDBCLibrary.service.ShellService;
import com.vasiliska.JDBCLibrary.service.ShellServiceImpl;
import com.vasiliska.JDBCLibrary.shell.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TestConfig {

    @Autowired
    private BookDaoJdbc dao;

    @Bean
    public ShellServiceImpl shellServiceImpl() {
        return new ShellServiceImpl(dao);
    }

    @Bean
    ShellService shellService() {
        return new ShellServiceImpl(dao);
    }

    @Bean
    public Commands commands() {
        return new Commands(shellService());
    }

}

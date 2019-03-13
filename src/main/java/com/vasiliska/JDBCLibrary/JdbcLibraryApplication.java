package com.vasiliska.JDBCLibrary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class JdbcLibraryApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(JdbcLibraryApplication.class, args);
    }
}

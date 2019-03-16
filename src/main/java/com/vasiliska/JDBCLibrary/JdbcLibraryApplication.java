package com.vasiliska.JDBCLibrary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackageClasses = {JdbcLibraryApplication.class})
public class JdbcLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcLibraryApplication.class, args);
    }
}

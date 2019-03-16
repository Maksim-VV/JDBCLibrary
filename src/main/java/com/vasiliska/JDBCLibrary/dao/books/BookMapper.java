package com.vasiliska.JDBCLibrary.dao.books;
import com.vasiliska.JDBCLibrary.domain.Author;
import com.vasiliska.JDBCLibrary.domain.Book;
import com.vasiliska.JDBCLibrary.domain.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        long authorId = resultSet.getLong("authorId");
        String nameAuthor = resultSet.getString("nameAuthor");

        long genreId = resultSet.getLong("genreId");
        String nameGenre = resultSet.getString("nameGenre");

        return new Book(id, name, new Author(authorId,nameAuthor),new Genre(genreId,nameGenre));
    }
}
package com.vasiliska.JDBCLibrary.dao;
import com.vasiliska.JDBCLibrary.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int genreId = resultSet.getInt("genreId");
        int authorId = resultSet.getInt("authorId");
        String nameAuthor = resultSet.getString("nameAuthor");
        String nameGenre = resultSet.getString("nameGenre");
        return Book.builder().id(id).name(name).genreId(genreId).authorId(authorId).authorName(nameAuthor).genreName(nameGenre).build();
    }
}
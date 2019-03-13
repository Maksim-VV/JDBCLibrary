package com.vasiliska.JDBCLibrary.dao;

import com.vasiliska.JDBCLibrary.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    final String INSERT_BOOK = "insert into books (`name`,`authorId`, `genreId`) values (:nameBook,:authorId,:genreId);";

    final String SELECT_ALL_BOOK = "SELECT b.id, b.name, b.authorId, b.genreId , at.nameAuthor, g.nameGenre \n" +
            " FROM BOOKS b,AUTHORS at, GENRES g \n" +
            "WHERE b.authorId = at.authorId\n" +
            "AND b.genreId  = g.genreId ";

    final String SEARCH_BOOK_BY_NAME = "SELECT b.id, b.name, b.authorId, b.genreId , at.nameAuthor, g.nameGenre \n" +
            "             FROM BOOKS b,AUTHORS at, GENRES g \n" +
            "            WHERE b.authorId = at.authorId\n" +
            "            AND b.genreId  = g.genreId \n" +
            "AND b.name = :nameBook";

    final String SEARCH_BOOK_BY_AUTHOR = "SELECT b.id, b.name, b.authorId, b.genreId , at.nameAuthor, g.nameGenre \n" +
            "             FROM BOOKS b,AUTHORS at, GENRES g \n" +
            "            WHERE b.authorId = at.authorId\n" +
            "            AND b.genreId  = g.genreId \n" +
            "AND at.nameAuthor = :nameAuthor";

    final String SEARCH_BOOK_BY_GENRE = "SELECT b.id, b.name, b.authorId, b.genreId , at.nameAuthor, g.nameGenre \n" +
            "             FROM BOOKS b,AUTHORS at, GENRES g \n" +
            "            WHERE b.authorId = at.authorId\n" +
            "            AND b.genreId  = g.genreId \n" +
            "AND g.nameGenre = :nameGenre";

    final String SEARCH_BOOK_BY_AUTHOR_ID = "select count(*) from books where authorId = :authorId";
    final String SEARCH_BOOK_BY_GENRE_ID = "select count(*) from books where genreId = :genreId";

    final String DELETE_BOOK = "delete from books where name = :nameBook";
    final String DELETE_AUTHOR = "delete from authors where authorId = :authorId";
    final String DELETE_GENRE = "delete from genres where genreId = :genreId";

    final String INSERT_AUTHOUR = "insert into authors (`nameAuthor`) values (:nameAuthor);";
    final String SEARCH_AUTHOR_ID_BY_NAME = "select authorId from authors where nameAuthor = :nameAuthor";

    final String INSERT_GENRE = "insert into genres (`nameGenre`) values (:nameGenre);";
    final String SELECT_GENRE_ID_BY_NAME = "select genreId from genres where nameGenre = :nameGenre";

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }


    @Override
    public List<Book> getAllBooks() {
        return jdbc.query(SELECT_ALL_BOOK, new BookMapper());
    }

    @Override
    public void insertBook(Book book) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("nameBook", book.getName());
        params.put("authorId", book.getAuthorId());
        params.put("genreId", book.getGenreId());
        jdbc.update(INSERT_BOOK, params);
    }

    @Override
    public Book getBookByName(String name) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("nameBook", name);
        Book book = Book.builder().name("").genreName("").authorName("").build();
        try {
            book = jdbc.queryForObject(SEARCH_BOOK_BY_NAME, params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            log.info("Сan't find  " + name + " book");
        }
        return book;
    }

    @Override
    public List<Book> getBookByAuthor(String nameAuthor) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("nameAuthor", nameAuthor);
        List<Book> booksList = new ArrayList<>();
        try {
            booksList = jdbc.query(SEARCH_BOOK_BY_AUTHOR, params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            log.info("Can't find author's book " + nameAuthor + " book");
        }
        return booksList;
    }

    @Override
    public List<Book> getBookByGenre(String nameGenre) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("nameGenre", nameGenre);
        List<Book> booksList = new ArrayList<>();
        try {
            booksList = jdbc.query(SEARCH_BOOK_BY_GENRE, params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            log.info("Can't find genre's book " + nameGenre + " book");
        }
        return booksList;
    }

    @Override
    public boolean delBook(String name) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("nameBook", name);
        int status = jdbc.update(DELETE_BOOK, namedParameters);
        if (status != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delAuthor(int authorId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("authorId", authorId);
        int status = jdbc.update(DELETE_AUTHOR, namedParameters);
        if (status != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delGenre(int genreId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("genreId", genreId);
        int status = jdbc.update(DELETE_GENRE, namedParameters);
        if (status != 0) {
            return true;
        }
        return false;
    }


    @Override
    public int getIdAuthor(String nameAuthor) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("nameAuthor", nameAuthor);
        int idAuthor = 0;
        try {
            idAuthor = jdbc.queryForObject(SEARCH_AUTHOR_ID_BY_NAME, namedParameters, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            if (log.isDebugEnabled()) {
                log.debug(e.toString());
            }
            insertAuthor(nameAuthor);
            return getIdAuthor(nameAuthor);
        }
        return idAuthor;
    }

    @Override
    public void insertAuthor(String nameAuthor) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("nameAuthor", nameAuthor);
        jdbc.update(INSERT_AUTHOUR, namedParameters);
    }

    @Override
    public int getIdGenre(String nameGenre) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("nameGenre", nameGenre);
        int idGenre = 0;
        try {
            idGenre = jdbc.queryForObject(SELECT_GENRE_ID_BY_NAME, namedParameters, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            if (log.isDebugEnabled()) {
                log.debug(e.toString());
            }
            insertGenre(nameGenre);
            return getIdGenre(nameGenre);
        }
        return idGenre;
    }

    @Override
    public void insertGenre(String nameGenre) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("nameGenre", nameGenre);
        jdbc.update(INSERT_GENRE, namedParameters);
    }

    @Override
    public int getCountBookByAuthor(int authorId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("authorId", authorId);
        int countBookByIdAuthor = 0;
        countBookByIdAuthor = jdbc.queryForObject(SEARCH_BOOK_BY_AUTHOR_ID, namedParameters, Integer.class);
        return countBookByIdAuthor;
    }

    @Override
    public int getCountBookByGenre(int genreId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("genreId", genreId);
        int countBookByIdGenre = 0;
        countBookByIdGenre = jdbc.queryForObject(SEARCH_BOOK_BY_GENRE_ID, namedParameters, Integer.class);
        return countBookByIdGenre;
    }

}

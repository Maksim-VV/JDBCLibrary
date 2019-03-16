package com.vasiliska.JDBCLibrary.dao.authors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    final String DELETE_AUTHOR = "delete from authors where authorId = :authorId";
    final String INSERT_AUTHOUR = "insert into authors (`nameAuthor`) values (:nameAuthor);";
    final String SEARCH_AUTHOR_ID_BY_NAME = "select authorId from authors where nameAuthor = :nameAuthor";

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public boolean delAuthor(long authorId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("authorId", authorId);
        int status = jdbc.update(DELETE_AUTHOR, namedParameters);
        if (status != 0) {
            return true;
        }
        return false;
    }

    @Override
    public long getIdAuthor(String nameAuthor) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("nameAuthor", nameAuthor);
        long idAuthor = 0;
        try {
            idAuthor = jdbc.queryForObject(SEARCH_AUTHOR_ID_BY_NAME, namedParameters, Long.class);
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


}

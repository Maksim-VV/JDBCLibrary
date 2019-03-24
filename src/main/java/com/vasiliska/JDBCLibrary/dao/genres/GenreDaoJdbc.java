package com.vasiliska.JDBCLibrary.dao.genres;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    private final String DELETE_GENRE = "delete from genres where genreId = :genreId";
    private final String INSERT_GENRE = "insert into genres (`nameGenre`) values (:nameGenre);";
    private final String SELECT_GENRE_ID_BY_NAME = "select genreId from genres where nameGenre = :nameGenre";

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }


    @Override
    public boolean delGenre(long genreId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("genreId", genreId);
        boolean status = false;
        try
        {
            if(jdbc.update(DELETE_GENRE, namedParameters) != 0)
            {
                status = true;
            }
        }
        catch(EmptyResultDataAccessException e)
        {
            status = false;
        }

        return status;
    }

    @Override
    public long getIdGenre(String nameGenre) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource("nameGenre", nameGenre);
        long idGenre = 0;
        try
        {
            idGenre = jdbc.queryForObject(SELECT_GENRE_ID_BY_NAME, namedParameters, Long.class);
        }
        catch (EmptyResultDataAccessException e)
        {
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

}

package com.vasiliska.JDBCLibrary.dao.genres;


public interface GenreDao {

    boolean delGenre(long genreId);

    long getIdGenre(String name);

    void insertGenre(String name);
}

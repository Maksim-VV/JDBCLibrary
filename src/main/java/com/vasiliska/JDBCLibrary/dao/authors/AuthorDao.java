package com.vasiliska.JDBCLibrary.dao.authors;


public interface AuthorDao {

    boolean delAuthor(long authorId);

    long getIdAuthor(String name);

    void insertAuthor(String name);
}

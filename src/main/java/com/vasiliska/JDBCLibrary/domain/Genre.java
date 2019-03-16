package com.vasiliska.JDBCLibrary.domain;

import lombok.Data;

@Data
public class Genre {
    private final long genreId;
    private final String genreName;

    public Genre(long genreId, String genreName)
       {
           this.genreId = genreId;
           this.genreName = genreName;
       }
}

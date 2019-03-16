DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE AUTHORS(authorId BIGINT auto_increment PRIMARY KEY, nameAuthor VARCHAR(255));
CREATE TABLE GENRES (genreId BIGINT auto_increment PRIMARY KEY, nameGenre VARCHAR(255));
CREATE TABLE BOOKS(id BIGINT auto_increment PRIMARY KEY, name VARCHAR(255), authorId BIGINT NOT NULL REFERENCES AUTHORS(authorId), genreId BIGINT NOT NULL REFERENCES GENRES(genreId));

insert into authors (`nameAuthor`) values ('Достоевский');
insert into genres (`nameGenre`) values ('Драма');
insert into books (`name`,`authorId`,`genreId`) values ('Преступление и наказание',1,1);

       
create database if not exists booksfortrade_db;
USE booksfortrade_db;


-- GRADELEVELS MODEL --
# DROP TABLE IF EXISTS grade_levels;

# CREATE TABLE IF NOT EXISTS grade_levels;
truncate roles;
INSERT INTO grade_levels(id, grade)
VALUES(1,1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10),
       (11, 11),
       (12, 12);

SELECT * FROM grade_levels;

-- TYPES MODEL --
truncate types;



INSERT INTO types(id, name)
VALUES(1,'Paperback'),
      (2, 'Hardback'),
      (3, 'e-Book');

SELECT * FROM types;


# CREATE TABLE IF NOT EXISTS roles;
truncate roles;
INSERT INTO roles(id,name)
VALUES(1, 'User'),
       (2, 'Admin'),
       (3, 'Inactive');


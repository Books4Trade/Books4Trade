USE booksfortrade_db;

DROP TABLE IF EXISTS gradeLevels;

CREATE TABLE IF NOT EXISTS gradeLevels;

INSERT INTO gradeLevels(id, grade)
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

SELECT * FROM gradeLevels;

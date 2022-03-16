# INITIAL INDEPENDENT TABLE VALUES
# This section runs all of the default value tables to which only we are currently adding values
# Users and other controllers do not add values to these tables

-- DO NOT RUN THIS MORE THAN ONCE --
-- DROP ALL TABLES AND RESTART THE APPLICATION BEFORE RUNNING --
-- TRUNCATE AND DROP OPTIONS HAVE BEEN REMOVED FOR MODEL-FOREIGN KEY CONSTRAINTS --

USE booksfortrade_db;

-- ROLE MODEL --
# Roles table- Connected to SecurityConfig for Granted Authorities ;
INSERT INTO roles(id,name)
VALUES(1, 'Admin'),
      (2, 'Editor'),
      (3, 'User'),
      (4,'Basic'),
      (5,'Banned'),
      (6, 'Inactive');
SELECT * FROM roles;

-- TYPE MODEL --
# Types - Connected to OwnedBooks
INSERT INTO types(id, name)
VALUES(1,'Paperback'),
      (2, 'Hardback'),
      (3, 'e-Book');
SELECT * FROM types;

-- GRADELEVELS MODEL --
# grade_levels Connects to Books for Future Search Implimentation
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

-- INITIAL ADMIN SETUP --
# Add the First 4 Users and Roles
INSERT INTO users(username, password, first_name, last_name, email, location)
VALUES('Julian1830','password', 'Julian','Martinez','JulianMartinez1830@gmail.com','San Antonio, TX'),
      ('achap86','password', 'Adam','Chappell','adam.chappell00@gmail.com','San Antonio, TX'),
      ('charlesjazper','password', 'Charles','Aggasid','charlesaggasid01@gmail.com','San Antonio, TX'),
      ('MichaelG1','password', 'Michael','Galimore','michaelgalimore1@gmail.com','San Antonio, TX');

INSERT INTO users_roles(user_id, role_id)
VALUES();
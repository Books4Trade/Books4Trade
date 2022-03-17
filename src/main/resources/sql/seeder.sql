# INITIAL INDEPENDENT TABLE VALUES
# This section runs all of the default value tables to which only we are currently adding values
# Users and other controllers do not add values to these tables

-- DO NOT RUN THIS MORE THAN ONCE --
-- DROP ALL TABLES AND RESTART THE APPLICATION BEFORE RUNNING --
-- TRUNCATE AND DROP OPTIONS HAVE BEEN REMOVED FOR MODEL-FOREIGN KEY CONSTRAINTS --

#USE booksfortrade_db;
USE swapabook_db;

-- ROLE MODEL --
# Roles table- Connected to SecurityConfig for Granted Authorities ;
INSERT INTO roles(id,name)
VALUES(1, 'ADMIN'),
      (2, 'EDITOR'),
      (3, 'USER'),
      (4,'BANNED'),
      (5, 'INACTIVE');
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
INSERT INTO users(id, enabled, username, password, first_name, last_name, email, location)
VALUES(1, true,'admin', '$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G','Administrator','Regulus','achap86@gmail.com', 'San Antonio, TX'),
        (2, true,'Julian1830','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Julian','Martinez','JulianMartinez1830@gmail.com','San Antonio, TX'),
        (3,true,'achap86','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Adam','Chappell','adam.chappell00@gmail.com','San Antonio, TX'),
        (4,true,'charlesjazper','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Charles','Aggasid','charlesaggasid01@gmail.com','San Antonio, TX'),
        (5, true,'MichaelG1','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Michael','Galimore','michaelgalimore1@gmail.com','San Antonio, TX');
SELECT * FROM users;

INSERT INTO users_roles(user_id, role_id)
VALUES(1,1),
       (2,1),
       (3,1),
       (4,1),
       (5,1);
SELECT * from users_roles;
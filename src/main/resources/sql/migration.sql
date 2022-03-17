-- MIGRATION SCRIPT --
# Use this to populate a starting data set for testing
# Last Update: March 16 2022
# Includes Users and their roles
#

USE swapabook_db;

INSERT INTO users(id, enabled, username, password, first_name, last_name, email, location)
VALUES  (6, true, 'HappyUser1','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Billy','Smith','billsmith@gmail.com','Austin, TX'),
        (7,true, 'SadUser4', '$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Doctor','No','JamesBond007@gmail.com','Waco, TX');

INSERT INTO users_roles(user_id, role_id)
VALUES (6,3),
       (7,4);
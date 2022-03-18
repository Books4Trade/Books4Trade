-- MIGRATION SCRIPT --
# Use this to populate a starting data set for testing
# Last Update: March 17 2022
# Includes Users and their roles
# Includes initial set of Authors and Their Books (MAINTIN ORDER INTEGRITY)

USE swapabook_db;

INSERT INTO users(id, enabled, username, password, first_name, last_name, email, location)
VALUES  (6, true, 'HappyUser1','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Billy','Smith','billsmith@gmail.com','Austin, TX'),
        (7,true, 'SadUser4', '$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Doctor','No','JamesBond007@gmail.com','Waco, TX');
SELECT * FROM users;

INSERT INTO users_roles(user_id, role_id)
VALUES (6,3),
       (7,4);
SELECT * FROM users_roles;

INSERT INTO authors(id, fullname)
VALUES(1,'Suzanne Collins'),
    (2,'J. K. Rowling'),
    (3,' Dr Seuss'),
    (4,'George Orwell'),
    (5,'Spencer Johnson'),
    (6,'C. S. Lewis'),
    (7,'Malcolm Gladwell'),
    (8,'Jared Diamond'),
    (9,'Miguel de Cervantes'),
    (10,'Joseph Conrad');
SELECT * FROM authors;

INSERT INTO books(id, book_img, summary, title, author_id)
VALUES(1, 'http://books.google.com/books/content?id=87eqoAEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','The final book in the international bestselling Hunger Games series is now a feature film! Against all odds, Katniss Everdeen has survived the Hunger Games twice. But now that shes made it out of the bloody arena alive, shes still not safe. The Capitol is angry. The Capitol wants revenge. Who do they think should pay for the unrest? Katniss. And whats worse, President Snow has made it clear that no-one else is safe either. Not Katnisss family, not her friends, not the people of District 12.','Mockingjay',1),
    (2,'http://books.google.com/books/content?id=35rHBAAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','Celebrate 20 years of Harry Potter magic! Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harrys eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and ...(more details were removed)','Harry Potter and the Philosophers Stone',2),
    (3,'http://books.google.com/books/content?id=OIwaMQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','No summary available.','Horton Hears a Who!',3),
    (4,'http://books.google.com/books/content?id=RCGpHZQZuDoC&printsec=frontcover&img=1&zoom=1&source=gbs_api','The classic science fiction story of a society in which' ,'1984',4),
    (5,'http://books.google.com/books/content?id=jw22QAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','One of the most-anticipated books in the history of publishing concludes the fantastic adventures of Harry Potter, the wizard whose fame rivals that of Merlin.','Harry Potter and the Deathly Hallows',2),
    (6,'http://books.google.com/books/content?id=n9J9AAAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','Relates a highly meaningful parable intended to help one deal with change quickly and prevail, offering readers a simple way to progress in their work and lives','Who Moved My Cheese?',5),
    (7,'http://books.google.com/books/content?id=lBS6qSKzQhcC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','Mere Christianity Journal is the ideal companion to Mere Christianity -- the beloved classic of Christian literature and the bestselling of all of Lewiss adult works. This readers journal is a celebration of one of Lewiss most popular and influential works. By serving as a thoughtful guide to further meditation on the central issues Lewis raises, this journal provides Lewis readers with a guide for deeper reflection. The journal includes an el...(more details were removed)','Mere Christianity Journal',6),
    (8,'http://books.google.com/books/content?id=3NSImqqnxnkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','From the bestselling author of The Bomber Mafia, learn what sets high achievers apart—from Bill Gates to the Beatles—in this seminal work from ','Outliers',7),
    (9,'http://books.google.com/books/content?id=XUR2vgEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','No summary available.' ,'Guns, Germs, and Steel',8),
    (10,'http://books.google.com/books/content?id=RwNKDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','A parody of chivalric romances, this story of an elderly knight and his loyal squire offers a strikingly modern narrative that also reflects the historical realities of 17th-century Spain.','Don Quixote',9),
    (11,'http://books.google.com/books/content?id=9ZU7PgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','Young wizard-in-training Harry Potter prepares for a competition between Hogwarts School of Magic and two rival schools, develops a crush on Cho Chang, and wishes above all to be a normal fourteen-year-old.','Harry Potter and the Goblet of Fire',2),
    (12,'http://books.google.com/books/content?id=zRQrMQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','Heart of Darkness a novella by Polish-British novelist Joseph Conrad, about a voyage up the Congo River into the Congo Free State, in the heart of Africa, by the storys narrator Marlow. Marlow tells his story to friends aboard a boat anchored on the River Thames, London, England. This setting provides the frame for Marlows story of his obsession with the ivory trader Kurtz, which enables Conrad to create a parallel between London and Africa as ...(more details were removed)','Heart of Darkness',10);
SELECT * from books;

INSERT INTO owned_books(id, book_condition,  is_tradeable , book_id, type_id, user_id)
VALUES(1, 'Good', true, 1, 1, 3);
SELECT * FROM owned_books;

INSERT INTO book_reviews(id, body, created_date, rating, title, book_id, user_id)
VALUES(1,'This book was amazing','2022-03-15',5,'LOVE!!!',1,3);
SELECT * FROM book_reviews;
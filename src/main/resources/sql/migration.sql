-- MIGRATION SCRIPT --
# Use this to populate a starting data set for testing
# Last Update: March 17 2022
# Includes Users and their roles
# Includes initial set of Authors and Their Books (MAINTIN ORDER INTEGRITY)

USE swapabook_db;

INSERT INTO users(id, enabled, username, password, first_name, last_name, email, location)
VALUES  (6, true, 'HappyUser1','$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Billy','Smith','billsmith@gmail.com','Austin, TX'),
        (7,true, 'SadUser4', '$2a$10$B6Y0tSaiGWc4CGmELUiMU.R8K./Dj7clhQPrCqswLrFKya8eaRi9G', 'Doctor','No','JamesBond007@gmail.com','Waco, TX'),
        (8,true, 'StarryNight32', '$2a$10$1ZFULxTVdic.FC56pezntO6Ou9DkQ8LAAVc6N8CpZcCzZBFSKSDrK','Kate','Sharp','vangogh-ing32@gmail.com','Pheonix, AZ'),
        (9, true, 'NanettLs', '$2a$10$FMBZ7JAnhgYTahsKApMkHeOgwVukh4jUz4Ngqcez7cLmvLUX5Y78.','Nana','Smith', 'booklover1942@gmail.com', 'Dallas, TX'),
        (10, true, 'Jynell7', '$2a$10$ACyYpCLUodvaDeqFa5IdCunIdMQMgw8qc7tGF1VIf4QlbFeL08gWq', 'Jynell', 'Peterson', 'Jyn07@gmail.com', 'Waco, TX'),
        (11, true, 'DrFizzy9', '$2a$10$D2nZSjn1KY/9a99R4JxzwOUKDx79q7tvEPTCjhu2jW1U/xw6hCno6', 'Phil', 'Stevens', 'Fizzenator99@gmail.com', 'San Antonio, TX'),
        (12, true, 'booklover1995', '$2a$10$WqFq9UZptL3Xvhuw5qaOHurhwcvFpkz3lobjqURKItjwtm9iiiQEG','Janey', 'McAusten', 'ilovebooks95@gmail.com', 'Austin, TX'),
        (13, true, 'Margaritaville84', '$2a$10$YqMkXrjMfMPH6ImlEF0VousTQ7rQ358tL9KTM8AcVgxxMPEjahbnO', 'Gary', 'Smith', 'Buffet84@gmail.com', 'San Antonio, TX');
SELECT * FROM users;

INSERT INTO users_roles(user_id, role_id)
VALUES (6,3),
        (7,4),
        (8,3),
        (9,3),
        (10,3),
        (11,3),
        (12,3),
        (13,3);
SELECT * FROM users_roles;

INSERT INTO authors(id, fullname)
VALUES(1,'Suzanne Collins'),
    (2,'J. K. Rowling'),
    (3,'Dr Seuss'),
    (4,'George Orwell'),
    (5,'Spencer Johnson'),
    (6,'C. S. Lewis'),
    (7,'Malcolm Gladwell'),
    (8,'Jared Diamond'),
    (9,'Miguel de Cervantes'),
    (10,'Joseph Conrad'),
    (11, 'Henry David Thoreau'),
    (12,'Téa Obreht'),
    (13,'Taylor Jenkins Reid'),
    (14,'Mary Laura Philpott'),
    (15,'Sophie Mackintosh'),
    (16,'Lauren Wilkinson'),
    (17,'JoAnn Chaney'),
    (18,'Claire Adam'),
    (19,'Nell Zink'),
    (20,'Karen Russell'),
    (21,'Julie Yip-Williams'),
    (22,'Sloane Tanen'),
    (23,'Karen Thompson Walker'),
    (24,'Marcel Proust'),
    (25,'James Joyce'),
    (26,'Herman Melville'),
    (27,'F. Scott Fitzgerald');
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

INSERT INTO books(id, book_img, summary, title, author_id)
VALUES(13,'http://books.google.com/books/content?id=HsfbyQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','When Sam-I-am pesters a grumpy grouch to eat a plate of green eggs and ham, we soon find out we can"t really know what we like until we try it!','Green Eggs and Ham',3),
        (14,'http://books.google.com/books/content?id=4jTgDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','NEW YORK TIMES BESTSELLER • The bestselling author of The Tiger’s Wife returns with “a bracingly epic and imaginatively mythic journey across the American West” (Entertainment Weekly). NAMED ONE OF THE BEST BOOKS OF THE YEAR BY Time • The Washington Post • Entertainment Weekly • Esquire • Real Simple • Good Housekeeping • Town & Country • The New York Public Library • Kirkus Reviews • Library Journal • BookPage In the lawless, drought-ridden land...(more details were removed)','Inland',12),
        (15,'http://books.google.com/books/content?id=IFJfDwAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','SOON TO BE AN AMAZON PRIME TV SERIES STARRING SAM CLAFLIN, RILEY KEOUGH AND CAMILA MORRONE THE SUNDAY TIMES AND NEW YORK TIMES BESTSELLER THE TIKTOK SENSATION From the author of THE SEVEN HUSBANDS OF EVELYN HUGO and the bestselling MALIBU RISING "I LOVE it . . . I can"t remember the last time I read a book that was so fun" DOLLY ALDERTON Everybody knows Daisy Jones and the Six. From the moment Daisy walked barefoot on to the stage at the Whisky, ...(more details were removed)','Daisy Jones and The Six',13),
        (16,'http://books.google.com/books/content?id=-9DVDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','NATIONAL BESTSELLER A charmingly relatable and wise memoir-in-essays by acclaimed writer and bookseller Mary Laura Philpott, “the modern day reincarnation of…Nora Ephron, Erma Bombeck, Jean Kerr, and Laurie Colwin—all rolled into one” (The Washington Post), about what happened after she checked off all the boxes on a successful life’s to-do list and realized she might need to reinvent the list—and herself. Mary Laura Philpott thought she’d cracke...(more details were removed)','I Miss You When I Blink',14),
        (17,'http://books.google.com/books/content?id=BRfBtAEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','Once upon a time, damaged women came here to be cured. We took them in, fed them glasses of our clean, good water, let them scream at the waves till their lips split like ripe fruit. Now no one is left but my sisters and me. King died a year ago, quite suddenly. Mother has vanished, no one knows where. And the safe compound they built around us, far away from the toxic world, has finally been breached. Three men arrived last week, washed up by th...(more details were removed)','The Water Cure',15),
        (18,'http://books.google.com/books/content?id=B2bRDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','“American Spy updates the espionage thriller with blazing originality.”—Entertainment Weekly “There has never been anything like it.”—Marlon James, GQ “So much fun . . . Like the best of John le Carré, it’s extremely tough to put down.”—NPR NAMED ONE OF THE TEN BEST BOOKS OF THE YEAR BY CHICAGO TRIBUNE AND ONE OF THE BEST BOOKS OF THE YEAR BY The New York Times Book Review • Time • NPR • Entertainment Weekly • Esquire • BuzzFeed • Vulture • Real ...(more details were removed)','American Spy',16),
        (19,'http://books.google.com/books/content?id=TRpGDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','“Unputdownable....This novel is anything but predictable. The female characters are forces of nature, and the plot twists are deliciously demented, a la Gone Girl and Big Little Lies.” —People You can’t be married to someone without sometimes wanting to kill them... As Long As We Both Shall Live is JoAnn Chaney’s wicked, masterful examination of a marriage gone very wrong, a marriage with lots of secrets... “My wife! I think she’s dead!” Matt fra...(more details were removed)','As Long as We Both Shall Live',17),
        (20,'http://books.google.com/books/content?id=paA5wwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','A TIMES AND EVENING STANDARD BOOK OF THE YEAR WINNER OF THE DESMOND ELLIOTT PRIZE 2019 ONE OF THE BBC`S `100 NOVELS THAT SHAPED OUR WORLD` LONGLISTED FOR THE EDINBURGH FESTIVAL FIRST BOOK AWARD "So hard to put down." - Daily Mail "Startling . . . Remarkable." Economist "Right away I was utterly absorbed." Sarah Jessica Parker One father. Two sons. An impossible choice. When thirteen-year-old Paul doesnt return home one afternoon, even his twin bro...(more details were removed)','Golden Child',18),
        (21,'http://books.google.com/books/content?id=tAR8DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','Named a Best Book of the Year by: The New York Times * New York Magazine * Lit Hub * TIME * O, the Oprah Magazine * Good Housekeeping Two generations of an American family come of age—one before 9/11, one after—in this moving and original novel from the “intellectually restless, uniquely funny” (New York Times Book Review) mind of Nell Zink Pam, Daniel, and Joe might be the worst punk band on the Lower East Side. Struggling to scrape together eno...(more details were removed)','Doxology',19),
        (22,'http://books.google.com/books/content?id=oZDyDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','This book presents eight of the author`s short stories. In' ,'Orange World and Other Stories',20),
        (23,'http://books.google.com/books/content?id=lrXPDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','NEW YORK TIMES BESTSELLER • Read with Jenna Book Club Pick as Featured on Today • As a young mother facing a terminal diagnosis, Julie Yip-Williams began to write her story, a story like no other. What began as the chronicle of an imminent and early death became something much more—a powerful exhortation to the living. “An exquisitely moving portrait of the daily stuff of life.”—The New York Times Book Review (Editors’ Choice) NAMED ONE OF THE BE...(more details were removed)','The Unwinding of the Miracle',21),
        (24,'http://books.google.com/books/content?id=oqNrDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','No summary available' ,'There`s a Word for That',22),
        (25,'http://books.google.com/books/content?id=b8BWDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','NEW YORK TIMES BOOK REVIEW EDITORS’ CHOICE • An ordinary town is transformed by a mysterious illness that triggers perpetual sleep in this mesmerizing novel from the bestselling author of The Age of Miracles. “Stunning.”—Emily St. John Mandel, author of Station Eleven • “A startling, beautiful portrait of a community in peril.”—Entertainment Weekly NAMED ONE OF THE BEST BOOKS OF THE YEAR BY Glamour • Real Simple • Good Housekeeping One night in a...(more details were removed)','The Dreamers',23),
        (26,'http://books.google.com/books/content?id=gPVBPgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','In this second volume of In Search of Lost Time, the narrator turns from the childhood reminiscences of Swann`s Way to memories of his adolescence. Having gradually become indifferent to Swanns daughter Gilberte, the narrator visits the seaside resort of Balbec with his grandmother and meets a new object of attention--Albertine, a girl with brilliant, laughing eyes and plump, matt cheeks. For this authoritative English-language edition, D. J. En...(more details were removed)','In Search of Lost Time',24),
        (27,'http://books.google.com/books/content?id=lEAyEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','ULYSSES James Joyce`s novel Ulysses is said to be one of the most important works in Modernist literature. It details Leopold Bloom`s passage through Dublin on an ordinary day: June 16, 1904. Causing controversy, obscenity trials and heated debates, Ulysses is a pioneering work that brims with puns, parodies, allusions, stream-of-consciousness writing and clever structuring. Modern Library ranked it as number one on its list of the twentieth cent...(more details were removed)','Ulysses',25),
        (28,'http://books.google.com/books/content?id=GL7BrQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api','Moby-Dick; or, The Whale is a novel by Herman Melville, in which Ishmael narrates the monomaniacal quest of Ahab, captain of the whaler Pequod, for revenge on the albino sperm whale Moby Dick, which on a previous voyage destroyed Ahabs ship and severed his leg at the knee. Although the novel was a commercial failure and out of print at the time of the authors death in 1891, its reputation grew immensely during the twentieth century. D. H. Lawre...(more details were removed)','Moby Dick',26),
        (29,'http://books.google.com/books/content?id=gnQJEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','The only authorized mass market edition of the twentieth-century classic, featuring F. Scott Fitzgerald’s final revisions, a foreword by his granddaughter, and a new introduction by National Book Award winner Jesmyn Ward. This edition of The Great Gatsby has been updated by F. Scott Fitzgerald scholar James L.W. West III to include the author’s final revisions and features a note on the composition and text, a personal foreword by Fitzgerald’s gr...(more details were removed)','The Great Gatsby',27),
        (30,'http://books.google.com/books/content?id=E9UiDQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','To coincide with the bicentennial of Thoreau`s birth and TarcherPerigee`s publication of Expect Great Things: The Life of Henry David Thoreau, here is a sumptuous rediscovery edition of the first illustrated volume of Thoreau`s classic, as originally issued in 1897. In 1897, thirty-five years after Thoreau`s death, Houghton Mifflin issued a two-volume','Walden',11);
SELECT * FROM books;

INSERT INTO owned_books(id, book_condition,  is_tradeable , book_id, type_id, user_id)
VALUES(1, 'Good', true, 1, 1, 3);
SELECT * FROM owned_books;

INSERT INTO book_reviews(id, body, created_date, rating, title, book_id, user_id)
VALUES(1,'This book was amazing','2022-03-15',5,'LOVE!!!',1,3);
SELECT * FROM book_reviews;
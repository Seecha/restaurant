SET SQL_SAFE_UPDATES = 0;

INSERT INTO address VALUES (1, 'HK', '63', 'Nakládalova třída', '50012');
INSERT INTO address VALUES (2, 'HK', 345, 'Kujónova street', 34444);

INSERT INTO user VALUES ('buh', 'buh@gmail.com', true, 'Jesus', '$2a$10$SisI0yLg59AfLgLVdxeeje8mV9x51P3G6voG8ogoajN/6cP0SOMKy', 'Christ', '696969696', 1);
INSERT INTO user VALUES ('cook1', 'cook1@gmail.com', 1, 'Honza', '$2a$10$SisI0yLg59AfLgLVdxeeje8mV9x51P3G6voG8ogoajN/6cP0SOMKy', 'Kuchař', 123456789, 2);
INSERT INTO user VALUES ('chef1', 'chef1@gmail.com', 1, 'Honza', '$2a$10$SisI0yLg59AfLgLVdxeeje8mV9x51P3G6voG8ogoajN/6cP0SOMKy', 'Šéfkuchař', 123456789, 2);
INSERT INTO user VALUES ('cook2', 'chef1@gmail.com', 1, 'Radek', '$2a$10$SisI0yLg59AfLgLVdxeeje8mV9x51P3G6voG8ogoajN/6cP0SOMKy', 'Kakrda', 123456789, 2);

INSERT INTO dinnertable VALUES (1, 4); 

INSERT INTO booking VALUES (1, '2014-12-12', 18, 22, 'buh', 1);

INSERT INTO delivery VALUES ('Osobni odber', 'Restaurant Gersthálka', 0.00, '777666555');
INSERT INTO delivery VALUES ('Rozvoz do domu', 'DameJidlo s.r.o.', 50.00, '741741741');

INSERT INTO customer_order VALUES (1, '2014-08-08', TRUE, 350.50, 'buh', 'Osobní odběr');

INSERT INTO role VALUES ('ROLE_ADMIN');
INSERT INTO role VALUES ('ROLE_USER');
INSERT INTO role VALUES ('ROLE_COOK');
INSERT INTO role VALUES ('ROLE_WAITER');
INSERT INTO role VALUES ('ROLE_CHEF');

INSERT INTO user_roles VALUES ('buh', 'ROLE_USER');
INSERT INTO user_roles VALUES ('cook1', 'ROLE_COOK');
INSERT INTO user_roles VALUES ('chef1', 'ROLE_CHEF');
INSERT INTO user_roles VALUES ('cook2', 'ROLE_COOK');
INSERT INTO user_roles VALUES ('buh', 'ROLE_ADMIN');

INSERT INTO dish_general VALUES ('1', 'MAIN', '120', '470', 'svickova', '220.50', '120', '38');
INSERT INTO dish_general VALUES ('2', 'DRINK', '35', '120', 'cocacola', '20.20', '36', '24');
INSERT INTO dish_general VALUES ('3', 'MAIN', '10', '270', 'rostena', '285.50', '140', '30');

INSERT INTO dishloc VALUES ('cs', '1', 'Svíčková výtečná za přihlížení Fapoleona', 'Svíčková na smetaně');
INSERT INTO dishloc VALUES ('en', '1', 'Swizckowa', 'Svickova');
INSERT INTO dishloc VALUES ('cs', '2', 'Coca cola výtečná za přihlížení Fapoleona', 'Cola na smetaně');
INSERT INTO dishloc VALUES ('en', '2', 'Coca', 'Cocs');
INSERT INTO dishloc VALUES ('cs', '3', 'Roštěná podávaná za skučení kujónů', 'Kujóní roštěná');
INSERT INTO dishloc VALUES ('en', '3', 'Roastbeaf - best while farting', 'Roastbeaf');

INSERT INTO ingredient_general VALUES (1, 15, 26, 12.50, 15, 78, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (2, 23, 45, 23.40, 34, 34, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (3, 73, 75, 10.35, 14, 21, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (4, 23, 34, 12.60, 4, 8, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (5, 13, 51, 14.20, 7, 2, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (6, 14, 23, 23.10, 6, 8, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (7, 23, 74, 2.60, 7, 8, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (8, 2, 21, 5.80, 5, 3, 'VEGETABLE');

INSERT INTO ingredientloc VALUES ('cs', 1, 'Mrkev');
INSERT INTO ingredientloc VALUES ('en', 1, 'Carrot');
INSERT INTO ingredientloc VALUES ('cs', 2, 'Brambory');
INSERT INTO ingredientloc VALUES ('en', 2, 'Potatos');
INSERT INTO ingredientloc VALUES ('cs', 3, 'Celer');
INSERT INTO ingredientloc VALUES ('en', 3, 'Celery');
INSERT INTO ingredientloc VALUES ('cs', 4, 'Dýně');
INSERT INTO ingredientloc VALUES ('en', 4, 'Pumpkin');
INSERT INTO ingredientloc VALUES ('cs', 5, 'Hrášek');
INSERT INTO ingredientloc VALUES ('en', 5, 'Pea');
INSERT INTO ingredientloc VALUES ('cs', 6, 'Cibule');
INSERT INTO ingredientloc VALUES ('en', 6, 'Onion');
INSERT INTO ingredientloc VALUES ('cs', 7, 'Česnek');
INSERT INTO ingredientloc VALUES ('en', 7, 'Garlic');
INSERT INTO ingredientloc VALUES ('cs', 8, 'Petržel');
INSERT INTO ingredientloc VALUES ('en', 8, 'Parsley');

INSERT INTO shift VALUES (1, 10, 22, 'THURSDAY');
INSERT INTO shift VALUES (2, 10, 22, 'TUESDAY');
INSERT INTO shift VALUES (3, 6, 20, 'WEDNESDAY');
INSERT INTO shift VALUES (4, 11, 19, 'SATURDAY');
INSERT INTO shift VALUES (5, 9, 17, 'SATURDAY');

INSERT INTO user_shift VALUES ('cook1', 1);
INSERT INTO user_shift VALUES ('cook1', 2);
INSERT INTO user_shift VALUES ('cook1', 3);
INSERT INTO user_shift VALUES ('chef1', 1);
INSERT INTO user_shift VALUES ('chef1', 4);
INSERT INTO user_shift VALUES ('cook1', 5);

INSERT INTO employee VALUES ('cook1', 'honza-kuchar');
INSERT INTO employee VALUES ('chef1', 'honza-sefkuchar');

INSERT INTO employeeloc VALUES ('cook1', 'cs', 'Honza Kuchař je velmi zkušený kuchař, specialista na kuřata a pštrosy.');
INSERT INTO employeeloc VALUES ('cook1', 'en', 'Honza Kuchař is an experienced cook. His speciality is chicken and ostrich.');
INSERT INTO employeeloc VALUES ('chef1', 'cs', 'Honza Šéfkuchař je velmi zkušený kuchař, specialista na hovězí. Jen je velký vtipálek.');
INSERT INTO employeeloc VALUES ('chef1', 'en', 'Honza Šéfkuchař is an experienced cook. His speciality is beef. He is called the Joker.');
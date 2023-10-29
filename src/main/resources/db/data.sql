-- SEMINARS
INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(1, 'Programming With Java', '150','Teacher :  Athanasios Androutsos', 'Code: 12121215642', 'Scientific Manager :   Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(2, 'Programming With Node', '100','Teacher : Markos Karampatsis', 'Code: 12121215642', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(3, 'Programming With Python', '80','Teacher : Athanasios Androutsos', 'Code: 12121215872', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(4, 'Programming With Javascript', '100','Teacher : Markos Karampatsis', 'Code: 12121215611', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(5, 'Programming With Angular ', '100','Teacher : Markos Karampatsis', 'Code: 12121215642', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(6, 'MySQL', '50','Teacher : Makis Kapetis', 'Code: 12121215612', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(7, 'Programming With C#', '80','Teacher : Athanasios Androutsos', 'Code: 12121215642', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(8, 'MongoDB', '80','Teacher : Markos Karampatsis', 'Code: 12121215124', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

INSERT INTO SEMINARS(id, name, price, teachers, code, scientific_manager,published_on)
    VALUES(9, 'CMS Drupal', '50','Teacher : Anna Giannoutsou', 'Code: 12121215787', 'Scientific Manager : Athanasios Androutsos', '2022-07-01');

-- USERS
INSERT INTO USERS (username, password, enabled)
	VALUES ('basil', '{noop}basil',1);


-- AUTHORITIES
INSERT INTO AUTHORITIES (username, authority)
	VALUES ('basil', 'ADMIN');




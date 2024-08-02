 CREATE SCHEMA EKL AUTHORIZATION sa;
 create table IF NOT EXISTS USER
 (
     ID UUID not null primary key,
     BIRTHDAY  DATE,
     EMAIL     CHARACTER VARYING(255),
     FIRSTNAME CHARACTER VARYING(255),
     LASTNAME  CHARACTER VARYING(255),
     PASSWD    CHARACTER VARYING(255),
     USERNAME  CHARACTER VARYING(255)
 );
-- insert into EKL_USER (birthday, email, firstname, lastname, passwd, username, id) values(null,null,'Vivian', 'Han', null, 'vivi','f68176ac-458a-4840-9cc4-a1c6b793333f');
-- insert into EKL_USER (birthday, email, firstname, lastname, passwd, username, id) values(null,null,'Yiyu', 'Ma', null, 'yiyu','f68176ac-458a-4840-9cc4-a1c6b793444f');

-- INSERT INTO "benutzer" (ID, BIRTHDAY, EMAIL, FIRSTNAME, LASTNAME, PASSWD, USERNAME) VALUES ('f68176ac-458a-4840-9cc4-a1c6b793333f', '2023-02-09', 'vivi@hotmail.com', 'Vivian', 'Han', '12345', 'vivi');
-- INSERT INTO "benutzer" (ID, BIRTHDAY, EMAIL, FIRSTNAME, LASTNAME, PASSWD, USERNAME) VALUES ('f68176ac-458a-4840-9cc4-a1c6b793444f', '2023-02-23', 'yiyu@hotmail.com', 'Yiyu', 'Ma', '67890', 'yiyu');
-- INSERT INTO "benutzer"  VALUES ('f68176ac-458a-4840-9cc4-a1c6b793333f', '2023-02-09', 'vivi@hotmail.com', 'Vivian', 'Han', '12345', 'vivi');
-- INSERT INTO "benutzer"  VALUES ('f68176ac-458a-4840-9cc4-a1c6b793444f', '2023-02-23', 'yiyu@hotmail.com', 'Yiyu', 'Ma', '67890', 'yiyu');
-- INSERT INTO "benutzer"  VALUES ('f68176ac-458a-4840-9cc4-a1c6b793366f', '2023-02-09', 'qiao@hotmail.com', 'Lulu', 'Wang', '12345', 'lulu');
-- INSERT INTO "benutzer"  VALUES ('f68176ac-458a-4840-9cc4-a1c6b793488f', '2023-02-23', 'han@hotmail.com', 'Mumu', 'Liu', '67890', 'mumu');
-- INSERT INTO "benutzer"  VALUES ('f68176ac-458a-4840-9cc4-a1c6b793499f', '2023-02-23', 'fan@hotmail.com', 'Fang', 'Chen', '67890', 'fanfan');

INSERT INTO "EKL"."USER"  VALUES ('f68176ac-458a-4840-9cc4-a1c6b793333f', '2023-02-09', 'Han','Vivian',  '12345', 'vivi@hotmail.com');
INSERT INTO "EKL"."USER"   VALUES ('f68176ac-458a-4840-9cc4-a1c6b793444f', '2023-02-23', 'Ma', 'Yiyu',  '12345', 'yiyu@hotmail.com' );

--INSERT INTO "EKL"."USER"   VALUES ('f68176ac-458a-4840-9cc4-a1c6b793366f', '2023-02-09', 'qiao@hotmail.com', 'Lulu', 'Wang', '12345', 'lulu');
--INSERT INTO "EKL"."USER"   VALUES ('f68176ac-458a-4840-9cc4-a1c6b793488f', '2023-02-23', 'han@hotmail.com', 'Mumu', 'Liu', '67890', 'mumu');
--INSERT INTO "EKL"."USER"   VALUES ('f68176ac-458a-4840-9cc4-a1c6b793499f', '2023-02-23', 'fan@hotmail.com', 'Meomo', 'Zhang', '67890', 'memo');



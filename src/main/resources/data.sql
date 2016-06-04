-- -- AKCIJE
--



-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','USER');
-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','ADMIN');
-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','ADMINISTRACIJA_KORISNIK');
-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','ADMINISTRACIJA_ULOGA');
-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','ADMINISTRACIJA_STATUS');
-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','ADMINISTRACIJA_VRSTA_DOKUMENTA');
-- INSERT INTO AKCIJA VALUES(AKCIJA_SEQ.nextval,'0','ADMINISTRACIJA_NOTIFIKACIJE');
--
--
-- -- uloga
--
-- INSERT INTO ULOGA VALUES (nextval(ULOGA_SEQ) ,'0','MOZE SVE');
-- INSERT INTO ULOGA VALUES (nextval(ULOGA_SEQ),'0','USER');
INSERT INTO uloga(id, deleted, naziv) VALUES (nextval('uloga_seq'),'0','MOZE SVE');
INSERT INTO uloga(id, deleted, naziv) VALUES (nextval('uloga_seq'),'0','USER');

INSERT INTO akcija(id, deleted, naziv) VALUES (nextval('akcija_seq'),'0','USER');
INSERT INTO akcija(id, deleted, naziv) VALUES (nextval('akcija_seq'),'0','ADMIN');

INSERT INTO uloga_x_akcija(akcija, uloga) VALUES (1,1);
INSERT INTO uloga_x_akcija(akcija, uloga) VALUES (1,2);
INSERT INTO uloga_x_akcija(akcija, uloga) VALUES (2,1);

-- INSERT INTO korisnik(id, email, enabled, password, username) VALUES (nextval('korisnik_seq'),'fg','1','$2a$11$Tyghjz8BILTukTHmSJB1wew/id9L2YrNfsWfhcdVoKhptsBY0ssIW','admin');
-- INSERT INTO korisnik(id, email, enabled, password, username) VALUES (nextval('korisnik_seq'),'jkj','1','$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','huma');

INSERT INTO KORISNIK VALUES (nextval('korisnik_seq'),NULL ,NULL,'neear1990@fleckens.hu','1','admin',NULL ,NULL,'$2a$11$Tyghjz8BILTukTHmSJB1wew/id9L2YrNfsWfhcdVoKhptsBY0ssIW','admin','admin' );
INSERT INTO KORISNIK VALUES (nextval('korisnik_seq'),NULL ,NULL,'teen1957@rhyta.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','huma' );
INSERT INTO KORISNIK VALUES (nextval('korisnik_seq'),NULL ,NULL,'husic2@gml.com','1','HUMA2',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA2','huma2' );

INSERT INTO korisnik_x_uloga(korisnik, uloga) VALUES (1,1);
INSERT INTO korisnik_x_uloga(korisnik, uloga) VALUES (2,2);

INSERT INTO STATUS VALUES(nextval('status_seq'), '0', 'Kreiran', 'Created');
INSERT INTO STATUS VALUES(nextval('status_seq'), '0', 'Potpisan', 'Signed');
INSERT INTO STATUS VALUES(nextval('status_seq'), '0', 'Poslan', 'Sent');
INSERT INTO STATUS VALUES(nextval('status_seq'), '0', 'Potvrdjen', 'Confirmed');
INSERT INTO STATUS VALUES(nextval('status_seq'), '0', 'Odbijen', 'Rejected');


-- INSERT INTO komentar(id, tekst) VALUES (nextval('status_seq'),'1');
-- INSERT INTO komentar(id, tekst) VALUES (nextval('status_seq'),'2');
-- INSERT INTO komentar(id, tekst) VALUES (nextval('status_seq'),'3');


-- (KORISNIK_SEQ.nextval,NULL ,NULL,'neear1990@fleckens.hu','1','admin',NULL ,NULL,'$2a$11$Tyghjz8BILTukTHmSJB1wew/id9L2YrNfsWfhcdVoKhptsBY0ssIW','admin','admin' );
-- -- INSERT INTO ULOGA VALUES (3,'0','EH3');
-- -- INSERT INTO ULOGA VALUES (4,'0','EH4');
-- -- INSERT INTO ULOGA VALUES (5,'0','EH5');
-- -- INSERT INTO ULOGA VALUES (6,'0','EH6');
-- -- INSERT INTO ULOGA VALUES (7,'0','EH7');
-- INSERT INTO ULOGA VALUES (8,'0','EH8');
--
-- -- korisnik
--
-- INSERT INTO KORISNIK VALUES
-- (KORISNIK_SEQ.nextval,NULL ,NULL,'neear1990@fleckens.hu','1','admin',NULL ,NULL,'$2a$11$Tyghjz8BILTukTHmSJB1wew/id9L2YrNfsWfhcdVoKhptsBY0ssIW','admin','admin' );
-- INSERT INTO KORISNIK VALUES
-- (KORISNIK_SEQ.nextval,NULL ,NULL,'teen1957@rhyta.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','huma' );
-- INSERT INTO KORISNIK VALUES
-- (KORISNIK_SEQ.nextval,NULL ,NULL,'huma.milisic2@gmail.com','1','HUMA2',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA2','huma2' );
-- -- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (2,NULL ,NULL,'1huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'g','HUMA','humta' );
-- -- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (3,NULL,NULL,'2huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','1huma' );
-- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (4,NULL ,NULL,'3huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','2huma' );
-- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (5,NULL,NULL,'4huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','3huma' );
-- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (6,NULL,NULL,'5huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','4huma' );
-- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (7,NULL,NULL,'6huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','h5uma' );
-- --
-- -- INSERT INTO KORISNIK VALUES
-- -- (8,NULL,NULL,'7huma.milisic@gmail.com','1','HUMA',NULL ,NULL,'$2a$10$VrVNE6bFjI8GsLujFb2qquB8/jYqJiyuJ6aYUBr5Rdysp2u33wRsS','HUMA','hu6ma' );
--
-- -- KORISNIK_X_ULOGA
--
-- INSERT INTO KORISNIK_X_ULOGA VALUES (1,1);
-- INSERT INTO KORISNIK_X_ULOGA VALUES (2,2);
-- -- INSERT INTO KORISNIK_X_ULOGA VALUES (2,2);
--
-- -- ULOGA_X_AKCIJA
--

-- INSERT INTO uloga_x_akcija
-- -- -- (uloga,akcija)
--  VALUES (1,1);
-- INSERT INTO ULOGA_X_AKCIJA
-- -- -- (uloga,akcija)
--  VALUES (1,2);
--  INSERT INTO ULOGA_X_AKCIJA VALUES (1,3);
--  INSERT INTO ULOGA_X_AKCIJA VALUES (1,4);
--  INSERT INTO ULOGA_X_AKCIJA VALUES (1,5);
--  INSERT INTO ULOGA_X_AKCIJA VALUES (1,6);
--  INSERT INTO ULOGA_X_AKCIJA VALUES (1,7);
--  INSERT INTO ULOGA_X_AKCIJA VALUES (2,1);
--
--
--  -- status
--  INSERT INTO STATUS VALUES(STATUS_SEQ.nextval, '0', 'Kreiran', 'Created');
--  INSERT INTO STATUS VALUES(STATUS_SEQ.nextval, '0', 'Potpisan', 'Signed');
--  INSERT INTO STATUS VALUES(STATUS_SEQ.nextval, '0', 'Poslan', 'Sent');
--  INSERT INTO STATUS VALUES(STATUS_SEQ.nextval, '0', 'Potvrdjen', 'Confirmed');
--  INSERT INTO STATUS VALUES(STATUS_SEQ.nextval, '0', 'Odbijen', 'Rejected');
--
--
--  -- vrsta dokumenta
-- -- INSERT INTO VRSTA_DOKUMENTA VALUES(1, 0, 'Rodni list', 'Rodni list', 'Template BA', 'Template EN');
-- -- INSERT INTO VRSTA_DOKUMENTA VALUES(2, 0, 'Zavrsni rad', 'Zavrsni rad', 'Template BA', 'Template EN');
--
-- -- notifikacija
-- -- INSERT INTO NOTIFIKACIJA VALUES(1, 0, 'Hitno pregledati');
-- -- INSERT INTO NOTIFIKACIJA VALUES(2, 0, 'Hitno hitno');
--
-- -- Komentari
--
-- -- INSERT INTO KOMENTAR VALUES(1, null, 'uraa');
--
-- -- Dokumenti
--
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
-- -- insert into dokument values(DOKUMENT_SEQ.nextval, null, null, null, null, null, null, null,'yeeeey');
--
--
-- -- Relacija Dokumenti
--
-- --insert into relacija_dokument values(1, 1, 'probaaaa', 'proba');
--
-- -- KorisnikXDokument
-- -- INSERT into KorisnikXDokument VALUES(1,1);
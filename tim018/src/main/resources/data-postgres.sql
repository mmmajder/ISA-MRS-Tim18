INSERT INTO Role (name) VALUES ('ROLE_ADMIN');/*1*/
INSERT INTO Role (name) VALUES ('ROLE_CLIENT');/*2*/
INSERT INTO Role (name) VALUES ('ROLE_FISHING_INSTRUCTOR');/*3*/
INSERT INTO Role (name) VALUES ('ROLE_BOAT_RENTER');/*4*/
INSERT INTO Role (name) VALUES ('ROLE_RESORT_RENTER');/*5*/
INSERT INTO Role (name) VALUES ('ROLE_USER');/*6*/

ALTER SEQUENCE userSeqGen RESTART;

/*admin*/
/*insert into users(id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled) values
				  (NEXTVAL('userSeqGen'), 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 'admin@gmail.com', '$2a$10$YFAN7RKvLMLfFotongSDdulPP9vgQGE312p.yUFbGfj.DGMUOKxd.', true);*/
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled) values
				   (NEXTVAL('userSeqGen'), 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 'admin@gmail.com', '$2a$10$YFAN7RKvLMLfFotongSDdulPP9vgQGE312p.yUFbGfj.DGMUOKxd.', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*client1*/
/*insert into users(id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled) values
				 (NEXTVAL('userSeqGen'), 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0, '0606611759', 'Serbia', 'Client', 'client1@gmail.com', '$2a$10$DchYGCyzdO61vQYRd2gLwOSEtgJwUxxXWC57d.fkCVzAwuL0qEgJu', true);*/
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, email, password, enabled) values
				   (NEXTVAL('userSeqGen'), 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0,  0, '0606611759', 'Serbia', 'Client', 'client1@gmail.com', '$2a$10$DchYGCyzdO61vQYRd2gLwOSEtgJwUxxXWC57d.fkCVzAwuL0qEgJu', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*fishman1*/
insert into fishing_instructor (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password, enabled) values  
				   (NEXTVAL('userSeqGen'), 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 'fishman1@gmail.com', '$2a$10$7nV0Gf8bB.E0g6i/iIwdbu3lK7ncYkyD1U/WMr31dwykugj.cDOzu', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*brenter1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled) values 
				  (NEXTVAL('userSeqGen'), 'BRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'BoatRenter', 'brenter1@gmail.com', '$2a$10$ri0aed9KXdcyNu9MT.5OWOUVIb6n7TT0XDr/uSq0Ht2OODHW90EYe', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 4);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*rrenter1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled) values 
				  (NEXTVAL('userSeqGen'), 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'ResortRenter', 'rrenter1@gmail.com', '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 5);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);




				   
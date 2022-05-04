

/*insert into user_account (email, is_deleted, password) values ('rrenter1@gmail.com', false, '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y');

insert into user_account (email, is_deleted, password) values ('fishman1@gmail.com', false, '$2a$10$7nV0Gf8bB.E0g6i/iIwdbu3lK7ncYkyD1U/WMr31dwykugj.cDOzu');*/
/*client1*/
/*insert into user_account (email, is_deleted, password) values ('client1@gmail.com', false, '$2a$10$DchYGCyzdO61vQYRd2gLwOSEtgJwUxxXWC57d.fkCVzAwuL0qEgJu');*/
/*brenter1*/
/*insert into user_account (email, is_deleted, password) values ('brenter1@gmail.com', false, '$2a$10$ri0aed9KXdcyNu9MT.5OWOUVIb6n7TT0XDr/uSq0Ht2OODHW90EYe');*/
/*admin*/
/*insert into user_account (email, is_deleted, password) values ('admin@gmail.com', false, '$2a$10$YFAN7RKvLMLfFotongSDdulPP9vgQGE312p.yUFbGfj.DGMUOKxd.');*/


/*insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (1, 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'ResortRenter', 1);
insert into fishing_instructor (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (2, 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 2);
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, user_account_id) values 
				   (3, 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0, 0, '0606611759', 'Serbia', 'Client', 3);
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values
				   (4, 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 4);*/
				   
/*CREATE SEQUENCE userSeqGen INCREMENT 1 START 1;*/

INSERT INTO Role (name) VALUES ('ROLE_ADMIN');/*1*/
INSERT INTO Role (name) VALUES ('ROLE_CLIENT');/*2*/
INSERT INTO Role (name) VALUES ('ROLE_FISHING_INSTRUCTOR');/*3*/
INSERT INTO Role (name) VALUES ('ROLE_BOAT_RENTER');/*4*/
INSERT INTO Role (name) VALUES ('ROLE_RESORT_RENTER');/*5*/

ALTER SEQUENCE userSeqGen RESTART;
/*rrenter*/
/*insert into Users (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password) values 
				  (NEXTVAL('userSeqGen'), 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'ResortRenter', 'rrenter1@gmail.com', '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y');*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password) values 
				  (NEXTVAL('userSeqGen'), 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'ResortRenter', 'rrenter1@gmail.com', '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 5);

/*fishman1*/
insert into fishing_instructor (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password) values  
				   (NEXTVAL('userSeqGen'), 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 'fishman1@gmail.com', '$2a$10$7nV0Gf8bB.E0g6i/iIwdbu3lK7ncYkyD1U/WMr31dwykugj.cDOzu');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 3);

/*insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password) values 
				   (1, 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'ResortRenter', 'rrenter1@gmail.com', false, '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y');
insert into fishing_instructor (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (2, 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 2);
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, user_account_id) values 
				   (3, 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0, 0, '0606611759', 'Serbia', 'Client', 3);
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values
				   (4, 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 4);*/
				   
				   
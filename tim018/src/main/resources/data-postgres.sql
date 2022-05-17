INSERT INTO Role (name) VALUES ('ROLE_ADMIN');/*1*/
INSERT INTO Role (name) VALUES ('ROLE_CLIENT');/*2*/
INSERT INTO Role (name) VALUES ('ROLE_FISHING_INSTRUCTOR');/*3*/
INSERT INTO Role (name) VALUES ('ROLE_BOAT_RENTER');/*4*/
INSERT INTO Role (name) VALUES ('ROLE_RESORT_RENTER');/*5*/
INSERT INTO Role (name) VALUES ('ROLE_USER');/*6*/
 
ALTER SEQUENCE userSeqGen RESTART;

/*admin*/
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled) values
				   (NEXTVAL('userSeqGen'), 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 'admin@gmail.com', '$2a$10$YFAN7RKvLMLfFotongSDdulPP9vgQGE312p.yUFbGfj.DGMUOKxd.', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*client1*/
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, email, password, enabled) values
				   (NEXTVAL('userSeqGen'), 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0,  0, '0606611759', 'Serbia', 'Client', 'client1@gmail.com', '$2a$10$DchYGCyzdO61vQYRd2gLwOSEtgJwUxxXWC57d.fkCVzAwuL0qEgJu', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*fishman1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password, enabled) values  
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


insert into asset_calendar (id) values (10001), (10002), (10003), (10004), (10005), (10006), (10007);

insert into resort (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id, number_of_rooms, number_of_beds) values
				  (1000000, 0, 35, 'Skadarlija 14, Beograd, Srbija', 4, 40, 'Jako lepa kuca', false, 'Beogradska kuca', 5, 'Nema pravila hehe', 10002, 5, 2, 5),
				  (1000001, 0, 42.5, 'Orchid Magu 7, Maadhad, 57887, Maldives', 4.7, 40, 'Jako lepa kuca', false, 'Maldivian hut on water', 2, 'Nema pravila hehe', 10003, 5, 5, 3),
				  (1000002, 0, 21, 'Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'All inclusive Brunei', 4, 'Nema pravila hehe', 10004, 5, 7, 2),
				  (1000003, 0, 65, 'Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'All inclusive Brunei', 4, 'Nema pravila hehe', 10005, 5, 4, 4),
				  (1000006, 0, 65, 'Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'Renter1 vila', 4, 'Nema pravila hehe', 10005, 5, 4, 4),
				  (1000007, 0, 65, 'Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'Renter1 vila 2', 4, 'Nema pravila hehe', 10005, 5, 4, 4);
				  
insert into boat (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id,
				  boat_type, length, num_of_motor, motor_power, max_speed, navigation_equipment, fishing_equipment) values
				  (1000004, 1, 35, 'Dubrovnik', 4, 40, 'Best ship to cruise around Adriatic sea', false, 'Dubrovnik Pirate', 15, 'No parties allowed', 10006, 4,
				  'Frigate', 15, 6, 50, 60, 'GPS, Sonar', 'Harpoon');	
				  
insert into adventure (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id,
				  fishing_equipment) values
				  (1000005, 2, 5, 'Backa Palanka', 4, 10, 'I will teach you how to fish som', false, 'SOM Fishing in Backa', 3, 'No smoking on board allowed', 10007, 3,
				  'Harpoon');	
				   
insert into registration (id, is_deleted, status, user_id, registration_date_time) values
	(1000001, false, 0, 3, now()::timestamp),
	(1000002, false, 0, 4, now()::timestamp);
	

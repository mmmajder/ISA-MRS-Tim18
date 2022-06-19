INSERT INTO Role (name) VALUES ('ROLE_ADMIN');/*1*/
INSERT INTO Role (name) VALUES ('ROLE_CLIENT');/*2*/
INSERT INTO Role (name) VALUES ('ROLE_FISHING_INSTRUCTOR');/*3*/
INSERT INTO Role (name) VALUES ('ROLE_BOAT_RENTER');/*4*/
INSERT INTO Role (name) VALUES ('ROLE_RESORT_RENTER');/*5*/
INSERT INTO Role (name) VALUES ('ROLE_USER');/*6*/
 
ALTER SEQUENCE userSeqGen RESTART;

/*images*/
/*insert into image(id, data, is_deleted, type, asset_id) values*/
	/*user images*/
	/*('1', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/default_admin.jpg'), false, null, null),
	('2', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/Katarina_Komad.jpg'), false, null, null),
	('3', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/fishman_ajder.jpg'), false, null, null),
	('4', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/default_user.jpg'), false, null, null),
	('5', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/default_admin.jpg'), false, null, null),
	*//*asset images*/
	/*('6', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/fishman_1.jpg'), false, null, 1000005),
	('7', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src/assets/images/fishman_2.jpg'), false, null, 1000005),
	('8', lo_import('C:/Users/Korisnik/Desktop/ISA/ISA-MRS-Project/ISA-MRS-Tim18/tim18-front/src\assets\images\fishman_3.jpg'), false, null, 1000005),
	('9', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\fishman_1.jpg'), false, null, 1000005),
	('10', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\fishman_2.jpg'), false, null, 1000005),
	('11', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\fishman_3.jpg'), false, null, 1000005),
	('12', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\skadarlija.jpg'), false, null, 1000000),
	('13', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\Maldives.jpg'), false, null, 1000001),
	('14', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\boat.jpg'), false, null, 1000004),
	('15', lo_import('C:\Users\Korisnik\Desktop\ISA\ISA-MRS-Project\ISA-MRS-Tim18\tim18-front\src\assets\images\default_admin.jpg'), false, null, null);
*/
/*admin*/
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled, profile_photo_id, already_logged) values
				   (NEXTVAL('userSeqGen'), 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 'admin@gmail.com', '$2a$10$YFAN7RKvLMLfFotongSDdulPP9vgQGE312p.yUFbGfj.DGMUOKxd.', true, '5', true);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*client1*/
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, email, password, enabled, profile_photo_id) values
				   (NEXTVAL('userSeqGen'), 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 30,  2, '0606611759', 'Serbia', 'Client', 'client1@gmail.com', '$2a$10$DchYGCyzdO61vQYRd2gLwOSEtgJwUxxXWC57d.fkCVzAwuL0qEgJu', true, '2');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*fishman1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password, enabled, profile_photo_id) values  
				   (NEXTVAL('userSeqGen'), 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 'fishman1@gmail.com', '$2a$10$7nV0Gf8bB.E0g6i/iIwdbu3lK7ncYkyD1U/WMr31dwykugj.cDOzu', true, '3');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*brenter1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled, profile_photo_id) values 
				  (NEXTVAL('userSeqGen'), 'BRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'BoatRenter', 'brenter1@gmail.com', '$2a$10$ri0aed9KXdcyNu9MT.5OWOUVIb6n7TT0XDr/uSq0Ht2OODHW90EYe', true, '4');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 4);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);	

/*rrenter1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled, profile_photo_id) values 
				  (NEXTVAL('userSeqGen'), 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 20, '060123456', 'Serbia', 'ResortRenter', 'rrenter1@gmail.com', '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y', true, '1');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 5);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);

/*renterForDelete*/
/*rrenter1*/
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, email, password,enabled, profile_photo_id) values 
				  (NEXTVAL('userSeqGen'), 'RRenterAddress 2', 'Backa Topola', 'Pera', false, 'Peric', 0, '060123456', 'Serbia', 'ResortRenter', 'rrenter2@gmail.com', '$2a$10$nHMUGlq4M/uWZBlO7y2Vu.L/XjSB6.9otsRDvC79Q2RMAhMBSNp3y', true, '15');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 5);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (CURRVAL('userSeqGen'), 6);


insert into asset_calendar (id) values (10001), (10002), (10003), (10004), (10005), (10006), (10007);

insert into resort (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id, number_of_rooms, number_of_beds) values
				  (1000000, 0, 35, 'Skadarlija 14, Beograd, Srbija', 4, 40, 'Jako lepa kuca', false, 'Beogradska kuca', 5, 'Nema pravila hehe', 10002, 5, 2, 5),
				  (1000001, 0, 42.5, 'Orchid Magu 7, Maadhad, 57887, Maldives', 4.7, 40, 'Jako lepa kuca', false, 'Maldivian hut on water', 2, 'Nema pravila hehe', 10003, 5, 5, 3);
				  
insert into boat (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id,
				  boat_type, length, num_of_motor, motor_power, max_speed, navigation_equipment, fishing_equipment) values
				  (1000004, 1, 35, 'Dubrovnik', 4, 40, 'Best ship to cruise around Adriatic sea', false, 'Dubrovnik Pirate', 15, 'No parties allowed', 10006, 4,
				  'Frigate', 15, 6, 50, 60, 'GPS, Sonar', 'Harpoon');	
				  
insert into adventure (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id,
				  fishing_equipment) values
				  (1000005, 2, 5, 'Backa Palanka', 4, 10, 'I will teach you how to fish som', false, 'SOM Fishing in Backa', 3, 'No smoking on board allowed', 10007, 3,
				  'Harpoon');	
				  
/*future*/
insert into time_range(from_date_time, is_deleted, to_date_time) values
					  ('2023-12-12', false, '2023-12-15');
insert into time_range(from_date_time, is_deleted, to_date_time) values
					  ('2022-11-11', false, '2022-11-21');
/*past*/
insert into time_range(from_date_time, is_deleted, to_date_time) values
					  ('2022-03-12', false, '2022-03-15');
insert into time_range(from_date_time, is_deleted, to_date_time) values
					  ('2022-04-04', false, '2022-04-08');
insert into time_range(from_date_time, is_deleted, to_date_time) values
					  ('2022-05-21 11:15', false, '2022-05-30 12:38');

/*special offer*/
insert into time_range(from_date_time, is_deleted, to_date_time) values
					  ('2022-06-11 11:15', false, '2022-06-20 12:38');
					  
			
insert into review(is_deleted, text, rating, is_complaint, is_client_writing, clientid, renterid, asset_id, status, didnt_show_up, reservation_id, version) values
						/*asset*/
						(false, 'Everything was fine.Everything was fine.Everything was fine.Everything was fine.Everything was fine.Everything was fine.Everything was fine.', 4, false, true, 2, null, 1000005, 1, false, 4,1),
						/*renter*/
						(false, 'Milan is a really cool guy!', 5, false, true, 2, 3, 1000005, 1, false, 4,1),
						
						/*asset*/  
						(false, 'Old boat', 2, true, true, 2, null, 1000005, 0, false, 4,1),
						/*renter*/
						(false, 'Rude renter! Rude renter! Rude renter! Rude renter! Rude renter! Rude renter! Rude renter!', 1, true, true, 2, 3, 1000005, 0, false, 4,1),
						
						/*client*/
						(false, 'Meh.', 3, false, true, 2, 3, 1000005, 0, false, 4,1),
						(false, 'Very bad.', 2, true, false, 2, 3, 1000005, 0, false, 4,1),
						(false, 'Awful.', 1, true, false, 2, 3, 1000005, 0, false, 4,1);
			
insert into loyalty_state (client_discount_percent, renter_discount_percent, tax_percent) values
	(5, 5, 50),
	(2, 7, 50),
	(2, 9, 50),
	(1, 1, 90),
	(3, 3, 50);
	
					  
insert into reservation(is_deleted, status, asset_id, asset_review_id, client_id, client_review_id, renter_review_id, time_range_id, total_price, cancelation_fee, loyalty_state_id) values
						(false, 0, 1000001, null, 2, null, null, 1, 100, 40, 1),
						(false, 0, 1000000, null, 2, null, null, 3, 150, 40, 2),
						(false, 0, 1000004, null, 2, null, null, 2, 200, 40, 3),
						(false, 0, 1000005, 1, 2, null, 2, 4, 250, 10, 4),
						(false, 0, 1000005, null, 2, null, null, 5, 150, 10, 5);
						
insert into asset_calendar_reserved(asset_calendar_id, reserved_id) values
	(10003, 1), (10004, 2), (10006, 3), (10007, 4), (10005, 5);
				   
insert into registration (id, is_deleted, status, user_id, registration_date_time) values
	(1000001, false, 0, 3, now()::timestamp),
	(1000002, false, 0, 4, now()::timestamp);

insert into deletation_request (is_deleted, reason, status, user_id, version) values
	(false, 'No reason', 0, 2,1),
	(false, 'Good reason', 0, 6,1);
	
/* prices */
insert into asset_price (id, asset_id, price, start_date, end_date) values
						('1', 1000005, 15, '2022-05-18', '2022-05-20'),
						('2', 1000005, 7, '2022-05-21', null),
						('3', 1000000, 9, '2022-05-21', null),
						('4', 1000001, 10, '2022-05-21', null),
						('5', 1000004, 3, '2022-05-21', null);

insert into loyalty_program (is_deleted, user_discount_type, discount, level, points) values
	(false, 0, 2, 'Bronze', 10),
	(false, 0, 5, 'Silver', 25),
	(false, 0, 10, 'Gold', 50),
	(false, 1, 2, 'Bronze', 10),
	(false, 1, 5, 'Silver', 20),
	(false, 1, 7.5, 'Gold', 40);
	
/*percent per reservation and points per reservation*/
insert into reservation_finances (points_per_reservation, reservation_tax) values
	(3, 40);
	
insert into special_offer (discount, is_deleted, other_services, asset_id, client_id, time_range_id) values
	(30, false, 'None', 1000005, 2, 6);

insert into asset_calendar_special_price(asset_calendar_id, special_price_id) values
	(10007, 1);
	
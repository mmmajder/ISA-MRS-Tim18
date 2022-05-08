insert into user_account (email, is_deleted, password) values ('rrenter1@gmail.com', false, 'rrenter1');
insert into user_account (email, is_deleted, password) values ('fishman1@gmail.com', false, 'fishman1');
insert into user_account (email, is_deleted, password) values ('client1@gmail.com', false, 'client1');
insert into user_account (email, is_deleted, password) values ('brenter1@gmail.com', false, 'brenter1');
insert into user_account (email, is_deleted, password) values ('admin@gmail.com', false, 'admin');

insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (1, 'RRenterAddress', 'Krivaja', 'Lazar', false, 'Mocevic', 0, '060123456', 'Serbia', 'ResortRenter', 1);
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (2, 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 2);
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, user_account_id) values 
				   (3, 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0, 0, '0606611759', 'Serbia', 'Client', 3);
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values
				   (4, 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 4);
				
insert into asset_calendar (id) values (10001), (10002), (10003), (10004), (10005), (10006), (10007);


insert into adventure (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id) values
			      (1, 2, 50, 'Asset Address', 3.5, 25, 'Description', false, 'Fishing adventure', 3, 'No rules', 10001, 2);

insert into resort (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id, number_of_rooms, number_of_beds) values
				  (2, 0, 35, 'Skadarlija 14, Beograd, Srbija', 4, 40, 'Jako lepa kuca', false, 'Beogradska kuca', 5, 'Nema pravila hehe', 10002, 1, 2, 5),
				  (3, 0, 42.5, 'Orchid Magu 7, Maadhad, 57887, Maldives', 4.7, 40, 'Jako lepa kuca', false, 'Maldivian hut on water', 2, 'Nema pravila hehe', 10003, 1, 5, 3),
				  (4, 0, 21, 'Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'All inclusive Brunei', 4, 'Nema pravila hehe', 10004, 1, 7, 2),
				  (5, 0, 65, 'Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'All inclusive Brunei', 4, 'Nema pravila hehe', 10005, 1, 4, 4);
				  
insert into boat (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id,
				  boat_type, length, num_of_motor, motor_power, max_speed, navigation_equipment, fishing_equipment) values
				  (6, 1, 35, 'Dubrovnik', 4, 40, 'Best ship to cruise around Adriatic sea', false, 'Dubrovnik Pirate', 15, 'No parties allowed', 10006, 1,
				  'Frigate', 15, 6, 50, 60, 'GPS, Sonar', 'Harpoon');	
				  
insert into adventure (id, asset_type, price, address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id,
				  fishing_equipment) values
				  (7, 2, 5, 'Backa Palanka', 4, 10, 'I will teach you how to fish som', false, 'SOM Fishing in Backa', 3, 'No smoking on board allowed', 10007, 1,
				  'Harpoon');	
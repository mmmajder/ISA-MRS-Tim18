insert into user_account (email, is_deleted, password) values ('rrenter1@gmail.com', false, 'rrenter1');
insert into user_account (email, is_deleted, password) values ('fishman1@gmail.com', false, 'fishman1');
insert into user_account (email, is_deleted, password) values ('client1@gmail.com', false, 'client1');
insert into user_account (email, is_deleted, password) values ('brenter1@gmail.com', false, 'brenter1');
insert into user_account (email, is_deleted, password) values ('admin@gmail.com', false, 'admin');

insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (1, 'RRenterAddress', 'Backa Topola', 'Aleksa', false, 'Stanivuk', 0, '060123456', 'Serbia', 'ResortRenter', 1);
insert into renter (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values 
				   (2, 'FishAdress', 'Novi Sad', 'Milan', false, 'Ajder', 0, '069876543', 'Serbia', 'FishingInstructor', 2);
insert into client (id, address, city, first_name, is_deleted, last_name, loyalty_points, penalty_points, phone_num, state, user_type, user_account_id) values 
				   (3, 'ClientAdress', 'Novi Sad', 'Katarina', false, 'Komad', 0, 0, '0606611759', 'Serbia', 'Client', 3);
insert into admin (id, address, city, first_name, is_deleted, last_name, loyalty_points, phone_num, state, user_type, user_account_id) values
				   (4, 'AdminAdress', 'Admingrad', 'Admin', false, 'Admin', 0, '06321654', 'Adminvil', 'Admin', 4);
				
insert into asset_calendar (id) values (10001);

insert into asset (address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, renter_id) values
				  ('Skadarlija 14, Beograd, Srbija', 4, 40, 'Jako lepa kuca', false, 'Beogradska kuca', 5, 'Nema pravila hehe', 1);
insert into asset (address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, renter_id) values
				  ('Orchid Magu 7, Maadhad, 57887, Maldives', 4.7, 40, 'Jako lepa kuca', false, 'Maldivian hut on water', 2, 'Nema pravila hehe', 1);
insert into asset (address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, renter_id) values
				  ('Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'All inclusive Brunei', 4, 'Nema pravila hehe', 1);
insert into asset (address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, renter_id) values
				  ('Brunei City 7, Yupi, 88887, Brunei', 3.7, 40, 'Jako lepa kuca', false, 'All inclusive Brunei', 4, 'Nema pravila hehe', 1);
				   
insert into asset (address, average_rating, cancelation_conditions, description, is_deleted, name, num_of_people, rules, calendar_id, renter_id) values
					('Asset Address', 0.0, 25, 'Description', false, 'Fishing adventure', 3, 'No rules', 10001, 2);

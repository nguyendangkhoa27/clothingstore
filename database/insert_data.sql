use clothingstore;
insert into role(name,code) values('Quan tri','ADMIN'),
							values('Nguoi dung','USER');

insert into user(first_name,last_name,email,phone_number,user_name,password,is_active) 
			values('Nguyen','Khoa','admin@gmail.com','ADMIN','123456',true),
			values('dung','thu','user@gmail.com','user','123456',true);
			
insert into user_role(user_id,role_id) values(1,1),values(2,2);
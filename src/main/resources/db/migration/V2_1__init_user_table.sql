insert into my_user (id, username, password, active,email, activation_code,name,info,rating)
values (1, 'admin', '$2a$08$Dbnbvv7JIkA2P4X7lI9niOoW0RAp/6rnzLy5ueKioIlxhjvURx952', true,'admin@gmail.com',null,'admin-name','info',100);
insert into my_user (id, username, password, active,email, activation_code,name,info,rating)
values (2, 'user','$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G' , true,'user@gmail.com','123','user-name','info',100);
insert into my_user (id, username, password, active,email, activation_code,name,info,rating)
values (3, 'vas','$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G' , true,'vas@gmail.com','345','vas-name','info',100);
insert into my_user (id, username, password, active,email, activation_code,name,info,rating)
values (4, 'mike','$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G' , true,'mike@gmail.com',null,'mike-name','info',100);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');
insert into user_role (user_id, roles)
values (2, 'USER');
insert into user_role (user_id, roles)
values (3, 'USER');
insert into user_role (user_id, roles)
values (4, 'USER');


insert into address_user (id,address,city,post_code,user_id,info)
values (1, '','','',1,'');
insert into address_user (id,address,city,post_code,user_id,info)
values (2, 'Пилипчука 21','Львів','000000',2,'');
insert into address_user (id,address,city,post_code,user_id,info)
values (3, 'Бандери 22','Київ','000001',3,'');
insert into address_user (id,address,city,post_code,user_id,info)
values (4, 'Миру 45 кв.56','Рівне','000004',4,'');

insert into phone_user (id,phone,user_id,info)
values (1, '1111111',1,'info'), (2, '121212',1,'info');
insert into phone_user (id,phone,user_id,info)
values  (3, '22222',2,'info');
insert into phone_user (id,phone,user_id,info)
values (4, '33333',3,'info');
insert into phone_user (id,phone,user_id,info)
values (5, '41414141',4,'info'), (6, '444444',4,'info');

insert into post_office_user (id,post_office,user_id,info)
values (1, '',1,'info'),(2, 'Нова почта',2,'info'),(3, 'Укр почта',3,'info'), (4, 'Деливери',4,'info'), (5, 'Укр почта',4,'info');
insert into my_user (id, username, password, active,email, activation_code,name,info,rating)
values (1, 'admin', '$2a$08$Dbnbvv7JIkA2P4X7lI9niOoW0RAp/6rnzLy5ueKioIlxhjvURx952', 1,'admin@gmail.com',null,'admin-name','info',100),
(2, 'user','$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G' , 1,'user@gmail.com','123','user-name','info',100),
(3, 'vas','$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G' , 1,'vas@gmail.com','345','vas-name','info',100),
(4, 'mike','$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G' , 1,'mike@gmail.com',null,'mike-name','info',100);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');
insert into user_role (user_id, roles)
values (2, 'USER');
insert into user_role (user_id, roles)
values (3, 'USER');
insert into user_role (user_id, roles)
values (4, 'USER');


insert into address_user (id,address,city,post_code,info,active,user_id)
values (1, '','','','',1,1),
        (2, 'Пилипчука 21','Львів','000000','',1,2),
        (3, 'Бандери 22','Київ','000001','',1,3),
        (4, 'Миру 45 кв.56','Рівне','000004','',1,4);

insert into phone_user (id,phone,active,info,user_id)
values  (1, '1111111',0,'info',1),
        (2, '121212',1,'info',1),
        (3, '22222',1,'info',2),
        (4, '33333',1,'info',3),
        (5, '41414141',1,'info',4),
        (6, '444444',0,'info',4);

insert into post_office_user (id,post_office,info,active,user_id)
values (1, '','info',1,1),
        (2, 'Нова почта','info',1,2),
        (3, 'Укр почта','info',1,3),
        (4, 'Деливери','info',1,4),
        (5, 'Укр почта','info',0,4);
insert into category (id, name,info)values (1, 'Збір',''),(2, 'Атлас',''),(3,'Вельвет',''),(4,'Трикотаж',''),(5,'Флис','');

insert into product (id,active,dat,name,color,purchase_price,selling_price,size_product,description,selling_size,category_id,info)
values (1, true ,'2021-04-12','Name','Черный',100,110,50,'',30.0,1,''),
  (2,true ,'2021-04-12', 'Name_2','Color_2',50,60,50,'Name_Color_2',20.0,1,''),
  (3,true ,'2021-04-12', 'Name_3','Color_3',50,60,50,'',20.0,1,''),
  (4,true ,'2021-04-12', 'Name_4','Color_4',100,110,50,'',10.0,1,''),
  (5,true ,'2021-04-12', 'Name_5','Color_5',50,60,50,'Name_Color_5',0.0,2,''),
  (6,true ,'2021-04-12', 'Name_6','Color_6',100,110,50,'',0.0,2,''),
  (7,true ,'2021-04-12', 'Name_7','Color_7',50,60,50,'Name_Color_7',0.0,2,''),
  (8,true ,'2021-04-12', 'Name_8','Color_8',100,110,50,'Name_Color_8',0.0,2,''),
  (9,true ,'2021-04-12', 'Name_9','Color_9',50,60,50,'Name_Color_9',0.0,3,''),
  (10,true ,'2021-04-12', 'Name_10','Color_10',100,110,50,'Name_Color_10',0.0,3,''),
  (11,true ,'2021-04-12', 'Name_11','Color_11',50,60,50,'Name_Color_11',0.0,3,''),
  (12,true ,'2021-04-12', 'Name_12','Color_12',50,60,50,'',0.0,3,''),
  (13,true ,'2021-04-12', 'Name_13','Color_13',50,60,50,'',0.0,1,''),
  (14,true ,'2021-04-12', 'Name_14','Color_14',100,110,50,'',0.0,2,''),
  (15,true ,'2021-04-12', 'Name_15','Color_15',50,60,50,'',0.0,3,''),
  (16,true ,'2021-04-12', 'Name_16','Color_16',100,110,50,'',0.0,1,''),
  (17,true ,'2021-04-12', 'Name_17','Color_17',100,110,50,'',0.0,2,''),
  (18,true ,'2021-04-12', 'Name_18','Color_18',50,60,50,'',0.0,3,''),
  (19,true ,'2021-04-12', 'Name_19','Color_19',100,110,50,'',0.0,1,''),
  (20,true ,'2021-04-12', 'Name_20','Color_20',100,110,50,'',0.0,3,'');

insert into image_product (id,img_product,showcase,info,amount,product_id)
values (1, '13_1.jpg',true,'',1,1),
  (2, '126_1.jpg',true,'',1,2),
  (3, '539.jpg',true,'',0,3),
  (4, '539_1.png',false,'',0,3),
  (5, '539_2.png',false,'',0,3),
  (6, '539_3.png',false,'',0,3),
  (7, '539_4.png',false,'',0,3),
  (8, '539_5.png',false,'',0,3),
  (9, '549.jpg',false,'',0,4),
  (10, '549_1.png',true,'',0,4),
  (11, '549_2.png',false,'',0,4),
  (12, '14.jpg',true,'',0,5),
  (13, '15.jpg',true,'',0,6),
  (14, '22_n.jpg',true,'',0,7),
  (15, '550.jpg',true,'',0,8),
  (16, '554.jpg',true,'',0,9),
  (17, '554_1.png',false,'',0,9),
  (18, '554_2.png',false,'',0,9),
  (19, '554_3.png',false,'',0,9),
  (20, '554_4.png',false,'',0,9),
  (21, '552.jpg',true,'',0,10),
  (22, '556.jpg',true,'',0,11),
  (23, '556_1.png',false,'',0,11),
  (24, '556_2.png',false,'',0,11),
  (25, '556_3.png',false,'',0,11),
  (26, '556_4.png',false,'',0,11),
  (27, '3.jpg',true,'',0,12),
  (28, 'n.jpg',true,'',0,13),
  (29, '520.jpg',true,'',0,14),
  (30, '568.jpg',true,'',0,15),
  (31, '569.jpg',true,'',0,16),
  (32, '570.jpg',true,'',0,17),
  (33, '3254.jpg',true,'',0,18),
  (34, 'noimage.png',true,'',0,19),
  (35, 'noimage.png',true,'',0,20);

insert into orders (id,user_id,address_id,phone_id,post_office_id,dat_dispatch,status,delivery,info,dat_create)
values (1, 2,2,3,2,'2021-04-13','на відправку','ТТН','info','2021-04-11'),
      (2, 3 ,3,4,3,'2021-04-20','на відправку','ТТН','info','2021-04-14');

insert into cart (id,order_id,product_id,name_product,sale_price,siz,discount_price,dat,info)
values (1, 1,1,'Name',110,30,0,'2021-04-11','info cart'),
  (2, 1,2,'Name_2',60,20,0,'2021-04-11','info cart'),
  (3, 1,3,'Name_3',60,10,0,'2021-04-11','info cart'),
  (4, 2,3,'Name_3',60,10,0,'2021-04-14','info cart'),
  (5, 2,4,'Name_4',100,10,0,'2021-04-14','info cart');

# insert into orders (id,user_id,address_id,phone_id,post_office_id,summ,dat_dispatch,status,delivery,info_order,dat_create)
# values (1, 2,2,3,2,5100,'2021-04-13','на відправку','ТТН','info','2021-04-11'),
#        (2, 3 ,3,4,3,1700,'2021-04-20','на відправку','ТТН','info','2021-04-14');
#
# insert into cart (id,order_id,product_id,name_product,sale_price,siz,summ,discount_price,dat,info_cart)
#     values (1, 1,1,'Name',110,30,3300,0,'2021-04-11','info cart'),
#            (2, 1,2,'Name_2',60,20,1200,0,'2021-04-11','info cart'),
#            (3, 1,3,'Name_3',60,10,600,0,'2021-04-11','info cart'),
#            (4, 2,3,'Name_4',110,10,1100,0,'2021-04-14','info cart'),
#            (5, 2,3,'Name_5',60,10,600,0,'2021-04-14','info cart');
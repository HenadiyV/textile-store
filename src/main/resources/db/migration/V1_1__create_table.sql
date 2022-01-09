create table my_user (
  id bigint not null auto_increment,
  activation_code varchar(255),
  active bit not null DEFAULT 1,
  email varchar(255),
  password varchar(255),
  username varchar(255),
  name varchar(255),
  info varchar(1024) DEFAULT '',
  rating integer DEFAULT 100,
  primary key (id)
);
create table user_role (
  user_id bigint not null,
  roles varchar(255)
);

create table address_user (
  id bigint not null auto_increment,
  city varchar(255),
  address varchar(255),
  post_code varchar(255),
  user_id bigint,
  info varchar(1024) DEFAULT '',
  primary key (id)
) ;

create table address_deleted (
  id bigint NOT NULL,
  address_id bigint NOT NULL,
  user_id bigint NOT NULL,
  city varchar(255) NOT NULL,
  post_code varchar(20) DEFAULT NULL,
  info varchar(255) DEFAULT '',
  dat_delete date NOT NULL,
  primary key (id)
);

create table phone_user (
  id bigint not null auto_increment,
  phone varchar(255),
  user_id bigint,
  info varchar(1024) DEFAULT '',
  active tinyint(1) NOT NULL DEFAULT 1,
  primary key (id)
);
create table phone_deleted (
  id bigint NOT NULL,
  phone_id bigint NOT NULL,
  user_id bigint NOT NULL,
  phone varchar(32) NOT NULL,
  info varchar(255) DEFAULT '',
  dat_delete date NOT NULL,
  primary key (id)
);

create table post_office_user (
  id bigint not null auto_increment,
  post_office varchar(255),
  user_id bigint,
  info varchar(1024) DEFAULT '',
  active tinyint(1) NOT NULL DEFAULT 1,
  primary key (id)
) ;
create table post_office_deleted (
  id bigint NOT NULL,
  post_office_id bigint NOT NULL,
  user_id bigint NOT NULL,
  post_office varchar(50) NOT NULL,
  info varchar(255) DEFAULT '',
  dat_delete date NOT NULL,
    primary key (id)
);



# create table message (
#   id bigint not null auto_increment,
#   filename varchar(255),
#   tag varchar(255),
#   text varchar(2048),
#   user_id bigint,
#   primary key (id)
# ) ;

create table category (
  id integer not null auto_increment,
  name varchar(255),
  info varchar(1024) DEFAULT '',
  primary key (id)
);

create table product (
  id bigint not null auto_increment,
  active bit not null,
  dat date,
  name varchar(255),
  color varchar(255),
  purchase_price double precision not null,
  selling_price double precision not null,
  size_product double precision not null,
  description varchar(2048),
  selling_size double ,
  info varchar(1024) DEFAULT '',
  category_id integer,
  primary key (id)
);

create table image_product (
  id bigint not null auto_increment,
  img_product varchar(255),
  showcase bit ,
  info varchar(1024)DEFAULT '',
  amount int default 0,
  product_id bigint, primary key (id)
);

create table orders (
  id bigint NOT NULL auto_increment,
  user_id bigint NOT NULL,
  phone_id bigint NOT NULL,
  address_id bigint NOT NULL,
  post_office_id bigint NOT NULL,
  dat_dispatch date NOT NULL,
  status varchar(30) DEFAULT NULL,
  delivery varchar(30) DEFAULT '',
  info varchar(255) DEFAULT '',
  dat_create date NOT NULL,
  primary key (id)
);

create table order_deleted (
  id bigint NOT NULL auto_increment,
  order_id bigint NOT NULL,
  user_id bigint NOT NULL,
  nick varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  phone varchar(50) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  post_office varchar(255) DEFAULT NULL,
  summ double NOT NULL,
  dat_create date NOT NULL,
  dat_delete date NOT NULL,
  status varchar(30) DEFAULT NULL,
  info varchar(255) DEFAULT '',
  canceled tinyint(1) NOT NULL DEFAULT 0,
  primary key (id)
);

create table cart (
  id bigint NOT NULL auto_increment,
  order_id bigint ,
  product_id bigint NOT NULL,
  name_product varchar(255),
  sale_price double NOT NULL,
   siz double NOT NULL,
  discount_price double DEFAULT NULL,
  dat date NOT NULL,
  info varchar(255) DEFAULT '',
  primary key (id)
);
create table cart_deleted (
  id bigint NOT NULL auto_increment,
  user_id bigint NOT NULL,
  order_id bigint NOT NULL,
  cart_id bigint NOT NULL,
  product_id bigint NOT NULL,
  name_product varchar(255) DEFAULT NULL,
  siz double NOT NULL,
  summ double NOT NULL,
  discount_price double DEFAULT NULL,
  dat_create date NOT NULL,
  dat_delete date NOT NULL,
  info varchar(255) DEFAULT '',
  primary key (id)
);


create table product_deleted (
  id bigint NOT NULL auto_increment,
  product_id bigint NOT NULL,
  name varchar(255) NOT NULL,
  length_cloth double NOT NULL,
  purchase_price double NOT NULL,
  selling_price double NOT NULL,
  sales_length double NOT NULL,
  dat date NOT NULL,
  info varchar(255) DEFAULT '',
  active tinyint(1) NOT NULL,
  singleton tinyint(1) NOT NULL,
  dat_delete date NOT NULL,
  primary key (id)
);

alter table product add constraint product_category_fk foreign key (category_id) references category (id);

alter table image_product add constraint image_product_product foreign key (product_id) references product (id);

alter table cart add constraint cart_order_fk foreign key (order_id) references orders (id);

alter table orders add constraint order_my_user_fk foreign key (user_id) references my_user (id);
alter table orders add constraint order_address_fk foreign key (address_id) references address_user (id);
alter table orders add constraint order_phone_fk foreign key (phone_id) references phone_user (id);
alter table orders add constraint order_post_office_fk foreign key (post_office_id) references post_office_user (id);

alter table address_user add constraint address_my_user_fk foreign key (user_id) references my_user (id);

# alter table message add constraint messagemy_user_fk foreign key (user_id) references my_user (id);

alter table user_role add constraint user_role_user_fk foreign key (user_id) references my_user (id);

alter table phone_user add constraint phone_my_user_fk foreign key (user_id) references my_user (id);

alter table post_office_user add constraint post_office_my_user_fk foreign key (user_id) references my_user (id);
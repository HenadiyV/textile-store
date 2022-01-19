create table basket(
  id bigint NOT NULL auto_increment,
  token varchar(255)NOT NULL,
  username varchar(1024) DEFAULT '',
  phone varchar(255) DEFAULT '',
  info varchar(1024) DEFAULT '',
  dat date,
  dat_clear date,
  user_id bigint,
    primary key (id)
);

create table basket_product(
  id bigint NOT NULL auto_increment,
  product_id bigint NOT NULL,
  size double NOT NULL,
  price double NOT NULL,
  img varchar(1024) DEFAULT '',
  info varchar(2048) DEFAULT '',
  dat date,
  basket_id bigint,
  primary key (id)
);

alter table basket_product add constraint basket_basket_product foreign key (basket_id) references basket (id);
create database r2s_mobile_store;
drop database r2s_mobile_store;
use r2s_mobile_store;
select * from users;
drop table users;
create table users(
	id varchar(40) primary key,
    username varchar(50) not null,
    password varchar(100) not null,
    role_id varchar(40) not null,
    create_at datetime default current_timestamp,
    foreign key (role_id) references roles(id)
);
select * from roles;
drop table roles;
insert into roles (id, name) values("dd476380-53e0-4503-8bb4-7939990f0b72", "USER");
create table roles(
	id varchar(40) primary key,
    name varchar(50) not null,
    create_at datetime default current_timestamp
);
select * from userdetails;
drop table userdetails;
delete from userdetails where user_id ="7a2238a5-8196-4f4d-a468-581585d1ab72";
create table userdetails(
	id varchar(40) primary key,
    user_id varchar(40) not null,
    firstname varchar(50) not null,
    middlename varchar(50) not null,
    lastname varchar(50) not null,
    address varchar(100) not null,
    phone varchar(10) not null,
    image_url varchar(255) not null,
    image_name varchar(100) not null,
    create_at datetime default current_timestamp,
    foreign key (user_id) references users(id)
);
select * from brands;
drop table brands;
create table brands(
	id varchar(40) primary key,
    name varchar(100) not null,
    create_at datetime default current_timestamp
);
drop table products;
select * from products;
create table products(
	id varchar(40) primary key,
    brand_id varchar(40) not null,
    name varchar(255) not null,
    description varchar(255) not null,
    price double not null,
    image_name varchar(100) not null,
    image_url varchar(255) not null,
    create_at datetime default current_timestamp,
    foreign key (brand_id) references brands(id)
);

drop table carts;
select * from carts;
create table carts(
	id varchar(40) primary key,
    user_id varchar(40) not null,
    total_product double not null,
    total_amount double not null,
    create_at datetime default current_timestamp,
    foreign key (user_id) references users(id)
);
drop table cartitem;
create table cartitems(
	id varchar(40) not null,
	cart_id varchar(40) not null,
    product_id varchar(40) not null,
    quantity int not null,
    primary key (cart_id, id),
    foreign key (product_id) references products(id),
    foreign key (cart_id) references carts(id)
);
create table status(
	id varchar(40) primary key,
    name varchar(100) not null,
    create_at datetime default current_timestamp
);
create table orders(
	id varchar(40) primary key,
    user_id varchar(40) not null,
    status_id varchar(40) not null,
    create_at datetime default current_timestamp,
    foreign key (user_id) references users(id),
    foreign key (status_id) references status(id)
);
create table orderitems(
	id varchar(40) not null,
	order_id varchar(40) not null,
    product_id varchar(40) not null,
    quantity double not null,
    primary key (order_id, id),
    foreign key (product_id) references products(id),
    foreign key (order_id) references orders(id)
);
drop table orderitems;

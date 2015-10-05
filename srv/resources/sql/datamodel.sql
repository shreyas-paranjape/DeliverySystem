drop schema public cascade;
create schema public;
create extension ltree;

create table base_stamp
(
	created timestamp,
	updated timestamp
);

create table site
(
	id serial primary key,
	latitude float,
	longitude float

)inherits(base_stamp);

create table address
(
	id serial primary key,
	add_line_one text,
	add_line_two text,
	site_id INT references site

)inherits(base_stamp);

create table customer
(
	id serial primary key,
	name varchar(300),
	email varchar(200),
	mobile varchar(20)

)inherits(base_stamp);

create table customer_address
(
	customer_id INT references customer,
	address_id INT references address,
	primary key (customer_id,address_id)

)inherits(base_stamp);


create table product
(
	id serial primary key,
	title varchar(300),
	description text,
	price float,
	restaurant_id int,
	category ltree,
	prep_time INT

) inherits(base_stamp);

create type restaurant_cuisine as enum('INDIAN','ORIENTAL','ITALIAN');

create table restaurant
(
	id serial primary key,
	title varchar(300),
	description text,
	site_id INT references site,
	cuisine restaurant_cuisine[]

) inherits(base_stamp);

create type ordr_status as enum('PLACED','IN_PROGRESS','COMPLETED');

create table ordr
(
	id serial primary key,
	customer_id INT references customer,
	status ordr_status

) inherits(base_stamp);

create type ordr_item_status as enum('NOT_ACK','IN_PREP','DECLINED','READY_FOR_PICKUP','DISPATHCED');

create table ordr_item
(
	order_id INT references ordr,
	product_id INT references product,
	status ordr_item_status,
	primary key (order_id,product_id)

) inherits(base_stamp);

create table site_matrix
(
  id serial primary key,
  one_site_id INT references site,
  two_site_id INT references site,
  cost float Not null

) inherits (base_stamp);

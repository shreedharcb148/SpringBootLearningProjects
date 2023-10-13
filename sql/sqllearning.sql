create database sqllearning;
use sqllearning;

create table customers(
	custNumber int not null primary key,
    custName varchar(255) not null,
    contactLastname varchar(255),
    contactfirstname varchar(255),
    phone int,
    address varchar(255),
    city varchar(255),
    state varchar(255),
    postalCode varchar(255),
    county varchar(255)

);

alter table customers
add column creditlimtit int;
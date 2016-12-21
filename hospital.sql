create database hospital

use hospital
create table account
(
   account varchar(20) primary key,
   password varchar(20),
   department varchar(20),
   name varchar(20),
   section varchar(20),
   sex varchar(10) 
)



create table medicine
(
   name varchar(20) primary key,
   count int,
   price int  
)
--drop table medicine
--insert into medicine values ('安神补脑片',1000,10);
--insert into medicine values ('小柴胡',1000,20);
--insert into medicine values ('999感冒灵',1000,20)
select * from account
insert into account(account,password,department,name,section,sex) values('asd','123','doctor','张三','内科','男')
insert into account(account,password,department,name,section,sex) values('asdf','123','doctor','李四','内科','男')
insert into account(account,password,department,name,section,sex) values('asdfg','123','doctor','王五','外科','男')
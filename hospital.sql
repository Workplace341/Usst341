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
--insert into medicine values ('������Ƭ',1000,10);
--insert into medicine values ('С���',1000,20);
--insert into medicine values ('999��ð��',1000,20)
select * from account
insert into account(account,password,department,name,section,sex) values('asd','123','doctor','����','�ڿ�','��')
insert into account(account,password,department,name,section,sex) values('asdf','123','doctor','����','�ڿ�','��')
insert into account(account,password,department,name,section,sex) values('asdfg','123','doctor','����','���','��')
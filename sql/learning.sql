create database schoolmanagement;
use schoolmanagement;
create table student(
	srollno int not null primary key,
    sfirstname varchar(255) not null,
    slastname varchar(255),
    sage int
    check (sage>10)
);

create table course(
	cid int not null primary key,
    srollno int not null,
    cname varchar(255) not null
);

insert into student values(1,"shreedhar","balekundri",18);
insert into student values(2,"akash","jadhav",18);
insert into student values(3,"akshay","joshi",18);
insert into student values(4,"vineet","kavishetty",18);
insert into student values(5,"megha","shahapurkar",18);
insert into student values(6,"nikita","rajuput",18);
insert into student values(7,"mallesh","naik",18);
insert into student values(8,"pradeep","nooli",18);
insert into student values(9,"prashant","malai",18);
insert into student values(10,"soniya","sangati",18);
insert into student values(11,"mahesh","gorav",18);


select * from student;

insert into course values(1,1,"maths");
insert into course values(2,2,"maths");
insert into course values(3,3,"maths");
insert into course values(4,4,"maths");
insert into course values(5,5,"maths");
insert into course values(6,6,"maths");
insert into course values(7,7,"maths");
insert into course values(8,8,"maths");
insert into course values(9,9,"maths");
insert into course values(10,10,"maths");
insert into course values(11,11,"maths");
insert into course values(12,12,"maths");
insert into course values(13,1,"science");
insert into course values(14,2,"science");
insert into course values(15,3,"science");
insert into course values(16,4,"science");
insert into course values(17,5,"science");
insert into course values(18,6,"science");
insert into course values(19,7,"science");
insert into course values(20,8,"science");
insert into course values(21,9,"science");
insert into course values(22,10,"science");
insert into course values(23,11,"science");
insert into course values(24,12,"science");
insert into course values(25,1,"kannada");
insert into course values(26,2,"kannada");
insert into course values(27,3,"kannada");
insert into course values(28,4,"kannada");
insert into course values(29,5,"kannada");
insert into course values(30,6,"kannada");
insert into course values(31,7,"kannada");
insert into course values(32,8,"kannada");
insert into course values(33,9,"kannada");
insert into course values(34,10,"kannada");
insert into course values(35,11,"kannada");
insert into course values(36,12,"kannada");
insert into course values(37,1,"SS");
insert into course values(38,2,"SS");

select * from course
order by cname desc;   

alter table course
add column cprize int;

update course
set cprize=100
where cid = 1







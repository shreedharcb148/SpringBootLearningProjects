use sqllearning;

create table employees(
	empNo int not null primary key,
    firstName varchar(255) not null,
    lastName varchar(255),
    extension varchar(255),
    email varchar(255),
    office_code int,
    reportsTo varchar(255),
    jobtitle varchar(255)
);

CREATE TABLE employees_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employeeNumber INT NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    changedat DATETIME DEFAULT NULL,
    action VARCHAR(50) DEFAULT NULL
);

-- before update trigger for employees table 

create trigger before_update_trigger_for_employees
	before update on employees
    for each row
    /*body of the trigger*/
    insert into employees_audit
    set action="update_before",
		employeeNumber = old.empNo,
        lastname = old.lastname,
        changedat = now();

-- after update trigger
create trigger after_update_trigger_for_employees
	after update on employees
    for each row
    /*body of the trigger*/
    insert into employees_audit
    set action="update_after",
		employeeNumber = new.empNo,
        lastname = new.lastname,
        changedat = now();
   
-- berfore insert
create trigger before_insert_trigger_for_employees
	before insert on employees
    for each row
    /*body of the trigger*/
    insert into employees_audit
    set action="insert_before",
		employeeNumber = new.empNo,
        lastname = new.lastname,
        changedat = now();
   
-- after insert
create trigger after_insert_trigger_for_employees
	after insert on employees
    for each row
    /*body of the trigger*/
    insert into employees_audit
    set action="insert_after",
		employeeNumber = new.empNo,
        lastname = new.lastname,
        changedat = now();
        
-- before delete
create trigger before_delete_trigger_for_employees
	before delete on employees
    for each row
    /*body of the trigger*/
    insert into employees_audit
    set action="delete_before",
		employeeNumber = old.empNo,
        lastname = old.lastname,
        changedat = now();
 
 -- after delete
create trigger after_delete_trigger_for_employees
	after delete on employees
    for each row
    /*body of the trigger*/
    insert into employees_audit
    set action="delete_after",
		employeeNumber = old.empNo,
        lastname = old.lastname,
        changedat = now();
        

show triggers;

update employees
set lastName = "jadhavvv"
where empNo=100;

insert into employees(empNo,firstName,lastName) values(103,"rohan","barki");

delete from employees where empNo=103;

select * from employees;

select * from employees_audit;






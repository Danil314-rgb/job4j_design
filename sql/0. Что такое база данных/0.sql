create table employee (
	id serial primary key,
	name varchar(255),
	age int,
	salary float,
	position text		
);

insert into employee (name, age, salary, position) 
values ('Bob','35', 27.000, 'Менеджер');
 
update employee set name = 'Tom', age = 32;

delete from employee;
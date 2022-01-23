create table departments(
	id serial primary key,
	name text
);

create table emploers(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments (name)
values ('security');

insert into departments (name)
values ('manager');

insert into departments (name)
values ('director');

insert into emploers (name, department_id)
values ('Tom', 1);

insert into emploers (name, department_id)
values ('Bob', null);

insert into emploers (name, department_id)
values ('Tommi', 3);

select * from emploers e
left join departments d
on e.department_id = d.id;

select * from emploers e
right join departments d
on e.department_id = d.id;

select * from emploers e
full join departments d
on e.id = d.id;

select * from emploers e
cross join departments d;

select d.name, e.department_id from departments d
left join emploers e 
on e.department_id = d.id
where e.department_id is null;

select * from emploers e
left join departments d
on e.id = d.id;

select * from emploers e
right join departments d
on e.id = d.id;

create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into teens (name, gender)
values ('Женя', 'муж');

insert into teens (name, gender)
values ('Саша', 'жен');

insert into teens (name, gender)
values ('Илья','муж');

insert into teens (name, gender)
values ('Даша', 'жен');

select n.name, n.gender, g.name, g.gender
from teens n
cross join teens g
where n.gender != g.gender;

create table fauna (
	id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
values ('fish', 10000, '1250-01-01');

insert into fauna (name, avg_age, discovery_date)
values ('cat', 100000, '750-01-01');

insert into fauna (name, avg_age, discovery_date)
values ('bear', 15000, '100-01-01');

insert into fauna (name, avg_age, discovery_date)
values ('monkey', 3000, '520-01-01');

insert into fauna (name, avg_age, discovery_date)
values ('dog', 10000, '600-01-01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';
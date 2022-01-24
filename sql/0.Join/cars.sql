create table body(
	id serial primary key,
	type text
);

create table engine(
	id serial primary key,
	power int
);

create table transmission(
	id serial primary key,
	total_gears int
);

create table car(
	id serial primary key,
	name text,
	body_id int references body(id) not null,
	engine_id int references engine(id) not null,
	transmission_id int references transmission(id) not null
);

insert into body (type)
values ('open');

insert into body (type)
values ('closed');

insert into body (type)
values ('open');

insert into body (type)
values ('closed');

insert into engine (power)
values (200);

insert into engine (power)
values (400);

insert into engine (power)
values (500);

insert into engine (power)
values (700);

insert into transmission (total_gears)
values (2);

insert into transmission (total_gears)
values (4);

insert into transmission (total_gears)
values (5);

insert into transmission (total_gears)
values (14);

insert into car (name, body_id, engine_id, transmission_id)
values ('toyota', 1, 2, 2);

insert into car (name, body_id, engine_id, transmission_id)
values ('mazda ', 2, 3, 3);

insert into car (name, body_id, engine_id, transmission_id)
values ('Камаз', 3, 4, 4);

select c.name, b.type, e.power, t.total_gears from car c
join body b
on c.body_id = b.id
join engine e
on c.body_id = e.id
join transmission t
on c.body_id = t.id;

select b.id, b.type, c.name from body b
full join  car c
on c.body_id = b.id
where c.name is null;

select e.id, e.power, c.name from engine e
full join car c 
on c.engine_id = e.id
where c.name is null;

select t.id, t.total_gears, c.name from transmission t
full join car c 
on c.transmission_id = t.id
where c.name is null;








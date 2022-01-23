insert into onwer (name)
values ('Tom');

insert into onwer (name)
values ('Bob');

insert into phone (name, onwer_id)
values ('Samsung S25', 1);

insert into phone (name, onwer_id)
values ('Samsung S21', 1);

insert into phone (name, onwer_id)
values ('Iphone 12', 2);

insert into phone (name, onwer_id)
values ('Iphone 12', 2);

create table onwer(
	id serial primary key,
	name text
);

create table phone (
	id serial primary key,
	name text,
	onwer_id int references onwer(id) 
);

select p.name, p.onwer_id, o.name, o.id
from phone as p
join onwer as o
on p.onwer_id = o.id;

select p.name, o.name
from phone as p
join onwer as o
on p.onwer_id = o.id;

select p.id, p.name, o.name
from phone as p 
join onwer as o
on p.onwer_id = o.id;
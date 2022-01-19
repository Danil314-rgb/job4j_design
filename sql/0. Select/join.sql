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
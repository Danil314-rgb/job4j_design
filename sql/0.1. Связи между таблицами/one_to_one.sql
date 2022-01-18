create table human (
	id serial primary key,
	name varchar(255)	
);

create table nose (
	id serial primary key,
	name varchar(255),
	human_id int references human(id) unique
);

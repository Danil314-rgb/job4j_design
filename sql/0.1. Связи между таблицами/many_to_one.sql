create table onwer (
	id serial primary key,
	name varchar(255)	
);

create table phones (
	id serial primary key,
	name varchar(255),
	onwer_id int references onwer(id)
);

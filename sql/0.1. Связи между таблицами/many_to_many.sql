create table students (
	id serial primary key,
	name varchar(255)	
);

create table desks (
	id serial primary key,
	name varchar(255)	
);

create table students_desks(
	id serial primary key,
	student_id int references students(id),
	desks_id int references desks(id)
);
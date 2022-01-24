create table type (
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type (name)
values ('Сыр');

insert into type (name)
values ('Молоко');

insert into type (name)
values ('Сладости');

insert into type (name)
values ('Овощи');

insert into product (name, type_id, expired_date, price)
values ('Сыр плавленный', 1, date '2022-01-31', 250);

insert into product (name, type_id, expired_date, price)
values ('Молоко Пестравка', 2, date '2021-12-31', 100);

insert into product (name, type_id, expired_date, price)
values ('Вишнёвое мороженое', 3, date '2024-01-31', 50);

insert into product (name, type_id, expired_date, price)
values ('Пюре - мороженое', 4, date '2022-01-20', 220);

insert into product (name, type_id, expired_date, price)
values ('Сыр тёртый', 1, date '2022-01-31', 240);

select p.name
from product p
join type t
on p.type_id = t.id
group by p.name, t.name
having t.name like 'Сыр';

select * from product 
where name like '%мороженое%';

select * from product
where expired_date < current_date;

select * from product
where price = (select max(price) from product);

select t.name, count(p.name)
from product p
join type t
on p.type_id = t.id
group by t.name;

select p.name, t.name
from product p
join type t
on p.type_id = t.id
where t.name like 'Молоко' or t.name like 'Сыр';

select t.name, count(p.name)
from product p
join type t
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select p.name, t.name
from product p
join type t
on p.type_id = t.id;
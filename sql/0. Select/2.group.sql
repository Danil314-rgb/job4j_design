create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people (name)
values ('Tom');

insert into people (name)
values ('Bob');

insert into devices (name, price)
values ('Samsung S25', 50000);

insert into devices (name, price)
values ('Samsung S21', 45000);

insert into devices (name, price)
values ('Iphone 12', 80000);

insert into devices (name, price)
values ('Iphone 12', 25000);

insert into devices_people (device_id, people_id)
values (1, 1);

insert into devices_people (device_id, people_id)
values (2, 1);

insert into devices_people (device_id, people_id)
values (3, 2);

insert into devices_people (device_id, people_id)
values (4, 2);

select avg(price)
from devices;

select p.name, avg(d.price)
from devices d
join devices_people dp
on d.id=dp.device_id
join people p
on dp.people_id=p.id
group by p.name;

select p.name, avg(d.price)
from devices d
join devices_people dp
on d.id=dp.device_id
join people p
on dp.people_id=p.id
group by p.name
having avg(d.price) > 50000;
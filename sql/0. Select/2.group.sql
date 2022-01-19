select avg(price)
from devices;

select avg(price), p.name
from devices d
join people p
on d.id = p.id
group by p.name;

select avg(price), p.name
from devices d
join people p
on d.id = p.id
group by p.name
having avg(price) > 50.000;

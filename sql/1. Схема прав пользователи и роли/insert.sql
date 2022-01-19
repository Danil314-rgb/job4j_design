insert into role(name) 
values ('Роль')
select * from role

insert into rules(name) 
values ('Право')
select * from rules

insert into role_rules(role_id, rules_id) 
values (1, 1)
select * from role_rules

insert into users(name, role_id) 
values ('Пользователь', 1)
select * from users

insert into category(name) 
values ('Категория')
select * from category

insert into state(name) 
values ('Состояние')
select * from state

insert into item(name, users_id, category_id, state_id) 
values ('Файл', 1, 1, 1)
select * from item

insert into comments(name, item_id) 
values ('Комментарий заявок', 1)
select * from comments

insert into attachs(name, item_id) 
values ('Заявка', 1)
select * from attachs













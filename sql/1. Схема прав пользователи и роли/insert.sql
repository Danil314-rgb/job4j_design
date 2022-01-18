insert into comments(name) 
values ('Комментарий заявок')
select * from comments

insert into attachs(name) 
values ('Заявка')
select * from attachs

insert into items(name, comments_id, attachs_id) 
values ('Заявка', 1, 1)
select * from items

insert into users(name, items_is) 
values ('Пользователь', 1)
select * from users

insert into role(name, users_id) 
values ('Роль', 2)
select * from role

insert into rules(name) 
values ('Право')
select * from rules

insert into role_rules(role_id, rules_id) 
values (1, 1)
select * from role_rules

insert into category(name, item_id) 
values ('Категория', 1)
select * from category

insert into state(name, item_id) 
values ('Состояние', 1)
select * from state


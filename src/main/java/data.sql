-- Заполнение тблицы roles
insert into roles (role_id, role_name) values ('1', 'USER');
insert into roles (role_id, role_name) values ('2', 'ADMIN');

-- Удаление роли USER у админа
DELETE FROM user_role WHERE user_id = 1;

-- Добавление роли пользователю
insert into user_role (role_id, user_id) values (2, 2);

-- Изменение денег
update wallet set quantity = 500 where user_id = 1



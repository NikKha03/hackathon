-- Заполнение тблицы roles
insert into roles (role_id, role_name) values ('1', 'USER');
insert into roles (role_id, role_name) values ('2', 'ADMIN');

-- Добавление роли пользователю
insert into user_role (role_id, user_id) values (2, 1);



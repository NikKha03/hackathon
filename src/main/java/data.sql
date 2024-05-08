-- Заполнение тблицы roles
insert into roles (role_id, role_name) values ('1', 'USER');
insert into roles (role_id, role_name) values ('2', 'ADMIN');

-- Добавление роли пользователю
insert into user_role (role_id, user_id) values (2, 1);

-- Добавление постоянных заданий
insert into tasks (task_id, taskTitle, taskDescription, taskCost, taskXp) values (1, "Кофемашина", "Задание помыть кофемашину", 250, 100);
insert into tasks (task_id, taskTitle, taskDescription, taskCost, taskXp) values (2, "Проверить почту", "Задание проверить почту на наличие гневных сообщений", 150, 50);
insert into tasks (task_id, taskTitle, taskDescription, taskCost, taskXp) values (3, "Исследование рынка",
        "Провести небольшое исследование рынка и подготовить краткий отчет с основными выводами.", 500, 300);
insert into tasks (task_id, taskTitle, taskDescription, taskCost, taskXp) values (4, "Опрос сотрудников", "Подготовить и провести опрос среди сотрудников по интересующей теме или инициативе.", 350, 200);
insert into tasks (task_id, taskTitle, taskDescription, taskCost, taskXp) values (5, "Анализ отзывов клиентов", "Проанализировать отзывы клиентов о продукте или услуге компании и выделить основные тренды.", 350, 200);

--Добавление товаров
insert into store (store_id, nameProduct, cost) values (1, "Футболка", 1500);
insert into store (store_id, nameProduct, cost) values (2, "Шорты", 1000);
insert into store (store_id, nameProduct, cost) values (3, "Бокал", 500;
insert into store (store_id, nameProduct, cost) values (4, "Стикеры", 300);
insert into store (store_id, nameProduct, cost) values (5, "Билет на концерт Стаса Михайлова", 3500);

--

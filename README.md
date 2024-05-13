## Веб приложение для корпоративной геймификации

### Настройка проекта
Проект работает на хосте: _localhost:8080/hackathon_

БД развернута в Docker'е, путь прописан в файле _application.yml_

_Sql_ запросы для частичного заполнения БД в файле _data.sql_

### Аккаунты, роли
+ #### Описание
  + Регистрация и авторизация с автоматической выдачей обычной роли (_user_)  
  + Выдача роли _admin_ в ручную, если потребуется  
  + Авторизация по _JWT_ токену
  + При регистрации начисляется 100 приветственных CloudCoin'ов 
  
+ #### Возможности пользователя с ролью _user_
  + Просмотр своего кошелька: 
  `@GetMapping(path = "/wallet")`
  + Просмотр и изменение дополнительной информации о себе:
  `@GetMapping(path = "/profile")`  и `@PostMapping(path = "/profile")`
  + Перевод CloudCoin'ов по email пользователя:
  `@PostMapping("/transfer/{email}")`
  + Просмотр истории входящих переводов от пользователей:
  `@GetMapping(path = "/incoming-transfer-history")`
  + Просмотр истории исходящих переводов пользователям:
  `@GetMapping(path = "/outgoing-transfer-history")`
  + Покупка товаров в магазине (_Store_) по id продукта:
  `@PostMapping("/store/{productId}")`
  + Просмотр истории покупок в магазине:
  `@GetMapping(path = "/purchase-history")`
  
+ #### Возможности пользователя с ролю _admin_
  + Просмотр всех пользователей:
  `@GetMapping("/all-users")`
  + Просмотр пользователя по email:
  `@GetMapping("/find-user/{email}")`
  + Удаление пользователя по email:
  `@DeleteMapping("/delete-user/{email}")`
  
___

### Игровая валюта,операции, история операций
+ #### Игровая валюта - _**CloudCoin**_  
  Описана в базе данных, в таблице - _Wallet_ (Кошелек)  
  `private String currency = "CloudCoin"`
+ #### Переводы (_Transfer_)
  + Напрямую привязаны к таблице _Purchase_, так как нужна история операций
  + Реализуется через контроллер _TransactionController_ по такому принципу:  
    Сначала создаем "заказ":  
    `dao.createPurchase(purchase, principal, email)`  
    Далее снимаем денги у того кто в данный момент совершает перевод:  
    `dao.writeOffMoney(purchase, principal)`  
    В конце зачисляем деньги получателю на счет по email:
    `dao.writeOnMoney(purchase, email)`

  + В _TransferDAO_ описаны методы зачисления и снятия валюты по id и email отправителя и получателя соответственно
  + Далее одной выборкой из бд можно будет получить историю всех операций происходящих с определенного аккаунта (_user_id_)

___

### Магазин для траты валюты
Таблица "_Store_" с товарами, категориями и ценами на них


___
### Топ пользователей по токенам
_*на данный момент нет реализации_

___
### Задания для получения CloudCoin'ов
Таблица с постоянными заданиями на каждый день: "_Tasks_"

_*на данный момент нет реализации_

___

### Схема БД
[//]: # (![Image alt]&#40;https://raw.githubusercontent.com/NikKha03/hackathon/master/image/BD.png;)
<img src="https://raw.githubusercontent.com/NikKha03/hackathon/master/image/BD.png" width="100%">













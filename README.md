# TestCRUDApp
Тестовое приложение CRUD для ДжаваРаш.
Автор: Максим 
Почта: mshkill@gmail.com
Скайп: dark_grey_wolf

Описание:

Прошу прощения за ту кулебяку, которая получилась, но у меня оставалась всего неделя, а технологии Spring+MVC, Hibernate, сборщик Maven, JPA, JSTL мне не были знакомы до этого, поэтому решал задачи "в лоб" и делал много ошибок. Конечно же, после этого я постараюсь досконально в них разобраться и освоить их получше. По той же причине (спешка) верстка кривовата, но ее я тоже отполирую.

Приложение работает с базой test на localhost:3306, таблица User, логин и пароль root, как и было указано в задании. Скрипт для создания таблицы и наполнения ее прилагается в корне проекта. Скрипт работает внутри базы test. СКРИПТ УДАЛЯЕТ ТАБЛИЦУ USER, ЕСЛИ ОНА СУЩЕСТВУЕТ!

Из известных мне багов, которые я просто не успел исправить (не успел разобраться, но тем не менее исправлю впоследствии): в случае перезапуска сервера, выбивает ошибку "HTTP Status 500 - Request processing failed; nested exception is java.lang.IllegalStateException: Cannot create a session after the response has been committed". Также, поле поиска (form input search) я реализовал криво, ибо оно завязано на path=name, на которое завязано и поле регистрации имени нового юзера, поэтому при возникновении ошибок при вводе в любое из полей значение дублируется в оба поля. Еще - приложение работает только с именами латиницей. Понял уже после того, как залил. Тоже исправлю.

Юнит тестов тоже не писал, опять же, боялся не успеть зарегистрироваться в реальном проекте, а ждать до лета просто нет возможности по личным обстоятельствам. Надеюсь на Ваше понимание и снисхождение :)

У меня проект отрабатывает нормально. Если я накосячил с заливкой проекта или есть еще какие-то проблемы с его деплоем, большая человеческая просьба - напишите мне в скайп или лучше даже на почту, я быстро отзовусь и поправлю свои ошибки. 

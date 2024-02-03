# Генератор паролей

---

Веб-Приложение, целью которого является хранение паролей пользователя.

### Стек технологий:

* back-end
  * Java (jdk version 19) <img src="readme/226777.png" style="width:20px; height:20px">
  * Spring framework (Проект работает на spring boot) <img src="readme/spring-icon.svg" style="width:20px; height:20px">
  * PostgreSQL (version 16) <img src="readme/Postgresql_elephant.svg" style="width:20px; height:20px">
  * Project Lombok <img src="readme/45949248.png" style="width:20px; height:20px">
* front-end
  * HTML <img src="readme/HTML5_Badge.svg" style="width:20px; height:20px">
  * CSS <img src="readme/CSS3_logo.svg" style="width:20px; height:20px">
  * Thymeleaf <img src="readme/thymeleaf-seeklogo.svg" style="width:20px; height:20px">

## Описание

Часто возникает ситуация, когда необходимо запомнить пароль от какого-то сайта и его некуда записать.
Это приложение помогает решить данную поблему. Оно позовляет удобно хранить пароли от сайтов для кадого пользователя.

### Адреса страниц приложения

* ```/auth/login``` - страница аутентификации
* ```/auth/registration``` - страница регистрации
* ```/web-info``` - страница со всеми сохранёнными сайтами пользователя
* ```/web-info/id``` - страница подробной информации о сайте
* ```/web-info/new``` - страница добавления нового сайта

##### Страницы для администраторов
* ```/people``` - страница всех пользователей
* ```/people/id``` - страница с информацией о конкнретном пользователе


## Запуск проекта

1. Установить ___[Docker](https://www.docker.com/products/docker-desktop/)___(до версии 4.22)
2. Открыть терминал в папке с проектом
3. выполнить команду

```shell
docker-compose up
```

---

### Сделано в личных интересах. 29.01.2024

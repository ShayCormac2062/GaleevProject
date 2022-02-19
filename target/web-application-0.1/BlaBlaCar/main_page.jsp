<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Эх, Прокачу!!!</title>
    <link rel="stylesheet" href="BlaBlaCar/style.css">
</head>
<body>

<header class="header">
    <h1>Эх, Прокачу!!!</h1>
    <div class="nav">
        <button id="writeTextButton" class="trip">Опубликовать поездку</button>
            <div class="nav1">
                <button id="loginButton" class="signup">${user.firstName}</button>
                <button id="registerButton" class="signup">Регистрация</button>
            </div>
        </div>
</header>

<main>
   <div class="container_1">
       <h6>С кем хотите и куда хотите!</h6>
   </div>

    <div class="container_2">
        <div class="block1">
            <h3>Ваша поездка по низкой цене</h3>
            <p>Куда бы вы ни собрались, вы сможете<br> найти свою идеальную поездку среди<br> множества маршрутов и доехать по<br> самой низкой цене</p>
        </div>
        <div class="block2">
            <h3>Доверяйте своим попутчикам</h3>
            <p>Мы стараемся узнать ваших будущих<br> попутчиков как можно лучше. Мы <br>проверяем отзывы, профили и паспортные<br> данные попутчиков, чтобы вы знали,<br> с кем поедете</p>
        </div>
        <div class="block3">
            <h3>В дорогу за пару кликов</h3>
            <p>Забронировать поездку проще простого.<br> В нашем приложении легко разобраться,<br> а всю работу на себя берет мощный<br> алгоритм - всего пара кликоа, и бронь<br> готова</p>
        </div>
    </div>

    <div class="container_3">
        <img src="https://images.unsplash.com/photo-1486496572940-2bb2341fdbdf?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80" alt="авто">
        <div class="block">
            <p>Хотите куда-нибудь поехать,<br> но не нашли попутчиков? <br>Напишите об этом <br>в нашем имейджборде!</p>
            <button id="goToChat" class="button">Перейти в общий чат</button>
        </div>
    </div>
</main>

<footer>
    <p>
        <a href="#">О нас</a> |
        <a href="#">Центр помощи</a> |
        <a href="#">Условия использования</a> |
        <a href="#">Пресса</a> |
        <a href="#">Ищем сотрудников</a>
    </p>
    <p class="footer">Эх, прокачу! 2021</p>
</footer>

<script type="text/javascript">
    document.getElementById("goToChat").onclick = function () {
        location.href = "/chat";
    }
    document.getElementById("writeTextButton").onclick = function () {
        location.href = "/post_ride";
    }
    document.getElementById("loginButton").onclick = function () {
        location.href = "/login";
    };
    document.getElementById("registerButton").onclick = function () {
        location.href = "/register";
    };
</script>

</body>
</html>
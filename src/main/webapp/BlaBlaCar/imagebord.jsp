<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Чат</title>
    <link rel="stylesheet" href="BlaBlaCar/style.css">
</head>

<body class="imagebord">

<div class="background">
    <div class="header-chat">
        <h1>Общий чат</h1>
    </div>

    <c:forEach var="message" items="${messages}">
        <div class="message">
            <div class="darker">
                <td>
                    <c:out value="${message.title}"/>
                </td>
            </div>
            <div class="darker">
                <td>
                    <c:out value="${message.text}"/>
                </td>
            </div>
            <div class="time-right">
                <td>
                    <c:out value="${message.timeOfCreate}"/>
                </td>
            </div>
        </div>
    </c:forEach>


    <div class="footer-chat">
        <div class="signup"><a href="/profile">Выйти из чата</a></div>
    </div>
</div>

</body>
</html>
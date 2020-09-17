<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 29.08.2020
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>registration_page</title>
    </head>
    <body>
        <c:if test="${requestScope.massage != null}">
            <strong>{requestScope.massage}</strong>
        </c:if>

        <form action="/reg" method="post">
            <input type="text" name="name" placeholder="name">
            <input type="text" name="login" placeholder="login">
            <input type="password" name="password" placeholder="password">
            <input type="submit" placeholder="submit">
        </form>
        <a href="/">Home</a>
    </body>
</html>

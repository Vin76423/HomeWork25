<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 29.08.2020
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>calc_page</title>
    </head>
    <body>
        <form action="/calc" method="post">
            <input type="text" name="num1" placeholder="num1" maxlength="5" minlength="1" required>
            <input type="text" name="num2" placeholder="num2">
            <input type="text" name="type" placeholder="type">
            <input type="submit" placeholder="submit">
        </form>

        <c:if test="${requestScope.result != null}">
            <br>
            <%--        <p><%=request.getAttribute("result")%></p>--%>
            <p>${requestScope.result}</p>
        </c:if>

        <a href="/">Home</a>
    </body>
</html>
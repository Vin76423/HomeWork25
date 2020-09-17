<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 10.09.2020
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>update_account_page</title>
</head>
<body>
    <c:if test="${requestScope.massage != null}">
        <strong>${requestScope.massage}</strong>
    </c:if>

    <form action="/updateAccount" method="post">
        <input name="value" type="text" placeholder="value" required maxlength="10" minlength="4">
        <select name="field">
            <option value="name">update name</option>
            <option value="login">update login</option>
            <option value="password">update password</option>
        </select>

        <button>update</button>
    </form>

    <a href="/account">Your account</a>
    <a href="/">Home</a>
</body>
</html>

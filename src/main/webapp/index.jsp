<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 29.08.2020
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>home_page</title>
</head>
<body>
Hello ${sessionScope.user.login}

<div>
    <c:if test="${sessionScope.user != null}">
        <a href="/account">UserAccount</a>
        <a href="/calc">Calc</a>
    </c:if>

    <c:if test="${requestScope.reportFlag}">
        <a href="/rep">Report</a>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <a href="/reg">Registration</a>
        <a href="/auth">Authorization</a>
    </c:if>
</div>
</body>
</html>

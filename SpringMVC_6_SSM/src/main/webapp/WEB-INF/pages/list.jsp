<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h3>查询所有成功</h3>

    <c:forEach items="${list}" var="account">
        ${account.name}<br>
        ${account.money}<br><hr>
    </c:forEach>
</body>
</html>

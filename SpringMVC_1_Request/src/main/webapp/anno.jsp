<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--常用的注解--%>

    <%--@RequestParam--%>
    <a href="anno/testRequestParam?name=haha">RequestParam</a>
    <br><hr>

    <%--@RequestBody--%>
    <h3>测试RequestBody注解</h3>
    <form action="anno/testRequestBody" method="post">
        用户姓名：<input type="text" name="uname"><br>
        用户年龄:<input type="text" name="age"><br>
        <input type="submit" value="上传"><br>
    </form>
    <br><hr>

    <%--@PathVariable--%>
    <h3>测试PathVariable注解</h3>
    <a href="anno/testPathVariable/哈哈">测试PathVariable注解</a>
    <br><hr>


    <%--@RequestHeader--%>
    <h3>测试RequestHeader注解</h3>
    <a href="anno/testRequestHeader">测试RequestHeader注解</a>
    <br><hr>


    <%--@CookieValue--%>
    <h3>测试CookieValue注解</h3>
    <a href="anno/testCookieValue">测试CookieValue注解</a>
    <br><hr>


    <%--@ModelAttribute--%>
    <h3>测试ModelAttribute注解</h3>
    <form action="anno/testModelAttribute" method="post">
        用户姓名：<input type="text" name="uname"><br>
        用户年龄:<input type="text" name="age"><br>
        <input type="submit" value="上传"><br>
    </form>
    <br><hr>

</body>
</html>

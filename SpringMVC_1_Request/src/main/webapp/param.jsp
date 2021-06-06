<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--请求参数基本类型绑定--%>
    <a href="param/testParam?username=123&password=456">请求参数绑定</a><br/>


    <%--请求参数绑定实体类型--%>
    <%--name中的名称要与实体类（domain包下的Account类）的属性相同--%>
    <h3>请求参数绑定实体类型,将数据封装到Account类中</h3>
    <form action="param/saveAccount" method="post">
        账号：<input type="text" name="username"><br/>
        密码：<input type="text" name="password"><br/>
        金额：<input type="text" name="money"><br/>
        用户姓名：<input type="text" name="user.uname"><br/>
        用户年龄：<input type="text" name="user.age"><br/>
        <input type="submit" value="上传">
    </form>
    <hr><br/>

    <h3>请求参数绑定实体类型,将数据封装到Account类中,类中存在list集合与map集合</h3>
    <form action="param/saveAccount" method="post">
        账号：<input type="text" name="username"><br/>
        密码：<input type="text" name="password"><br/>
        金额：<input type="text" name="money"><br/>

                <h2>User对象存入list集合中</h2>
        用户姓名：<input type="text" name="list[0].uname"><br/><%--将uname存入User对象中，再将User对象放入list集合的0位置上--%>
        用户年龄：<input type="text" name="list[0].age"><br/>

                 <h2>User对象存入map集合中</h2>
        用户姓名：<input type="text" name="map['one'].uname"><br/><%--将uname存入User对象中，再将User对象放入map集合中key为one的位置上--%>
        用户年龄：<input type="text" name="map['one'].age"><br/>

        <input type="submit" value="上传">
    </form>
    <hr><br/>


    <h3>自定义类型转换</h3>
    <form action="param/saveUser" method="post">
        用户姓名：<input type="text" name="uname"><br/>
        用户年龄：<input type="text" name="age"><br/>
        用户生日：<input type="text" name="birthday"><br/>
        <input type="submit" value="上传">
    </form>
    <hr><br/>


    <h3>获取Servlet原生的API</h3>
    <a href="param/testServlet">获取servlet</a>


</body>
</html>

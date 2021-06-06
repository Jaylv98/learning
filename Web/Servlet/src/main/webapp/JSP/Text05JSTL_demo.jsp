<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="JSP.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>test</title>
</head>
<body>


    <%--需求：在request域中有一个存有User对象的List集合，需要使用Jstl+el将list集合数据展示到jsp页面的表格table中--%>
    <%
        List list=new ArrayList();
        list.add(new User("张三",24,new Date()));
        list.add(new User("李四",29,new Date()));
        list.add(new User("王五",54,new Date()));

        request.setAttribute("list",list);
    %>


    <table border="1" width="500" align="center">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>生日</th>
        </tr>
        <%--数据行--%>
        <c:forEach items="${list}" var="user" varStatus="s">

            <c:if test="${s.count%2==0}">
                <tr bgcolor="#00bfff">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birStr}</td>
                </tr>

            </c:if>


            <c:if test="${s.count%2!=0}">
                <tr bgcolor="gray">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birStr}</td>
                </tr>

            </c:if>




        </c:forEach>
    </table>
</body>
</html>

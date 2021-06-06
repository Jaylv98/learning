<%@ page import="JSP.User" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL获取值——对象</title>
</head>
<body>

<%--


            获取值：el表达式只能从域对象中获取值
                    语法：
                        ${域名称.键名}：从指定域中获取指定键的值
                            域名称：
                                pageScope	-->pageContext
                                requestScope	-->request
                                sessionScope	-->session
                                applicationScope	-->application(ServletContext)
                                如：在request域中存储了name=张三
                                获取：${request.name}
                        ${键名}：表示依次从最小的域中查找是否有该键对应的值，直到找到为止
                        ${name}
                    获取对象，List集合，Map集合的值
                            对象：${域名称.键名.属性名}
                            本质上会去调用对象的getter方法
                    List集合：
                            ${域名称.键名[索引]}
                    Map集合：
                            ${域名称.键名称.key名称}
                            ${域名称.键名称.[key名称]}

--%>





    <%
        User user=new User();
        user.setName("张三");
        user.setAge(56);
        user.setBirthday(new Date());
        request.setAttribute("user",user);


        List list=new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add(user);
        request.setAttribute("list",list);

        Map map=new HashMap();
        map.put("sname","李四");
        map.put("gender","男");
        request.setAttribute("map",map);


        String str="";
    %>

    <h3>el获取对象中的值</h3>
    ${requestScope.user}

<%--
        通过的是对象的属性来获取
            setter或getter方法，去掉set或get，再将剩余部分首字母变为小写
            setName-->Name-->name
--%>
    ${requestScope.user.name}<br>
    ${user.age}<br>
    ${user.birthday}<br>
    ${user.birthday.month}<br>

    ${user.birStr}<br>



    <h3>el获取List集合中的值</h3>
    ${list}<br>
    ${list[0]}<br>
    ${list[1]}<br>
    ${list[10]}<br><%--索引越界不会报错，显示空字符串--%>

    ${list[2]}<br>
    ${list[2].name}<br>


    <h3>el获取Map集合中的值</h3>
    ${map.gender}<br>
    ${map.["gender"]}<br>


        <%--
                    空运算符：empty
					    功能：用于判断字符串，集合，数组对象是否为null并且长度为0
					        ${empty list}：判断字符串，集合，数组对象是否为null或长度为0
					        ${not empty str}：判断字符串，集合，数组对象是否为nul并且长度>0


        --%>

    <h3>empty运算符</h3>
    ${empty list}
    ${not empty str}
</body>
</html>

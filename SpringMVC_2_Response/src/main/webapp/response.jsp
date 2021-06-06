<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%--引入js--%>
    <script src="js/jquery.min.js"></script>

    <%--模拟异步Ajax请求--%>
    <script>
        //页面加载，绑定单击事件
        $(function () {
            $("#btn").click(function () {
                //alert("HELLO");
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/testAjax",//路径
                    contentType:"application/json;charset=UTF-8",//发送信息至服务器端时的内容编码类型
                    data:'{"username":"lisi","password":"123","age":"26"}',//发送到服务器的数据
                    datatype:"json",//预期服务器返回的数据类型
                    type:"post",//请求方式
                    //请求成功后的回调函数
                    success:function (data) {
                        //data指服务器端响应的json数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }
                });
            });
        });
    </script>
</head>
<body>

    <%--响应返回值为String类型--%>
    <h3>测试返回值为String类型</h3>
    <a href="user/testString">testString</a>
    <br><hr>


    <%--响应返回值为Void类型--%>
    <h3>测试返回值为Void类型</h3>
    <a href="user/testVoid">testVoid</a>
    <br><hr>


    <%--响应返回值为ModelAndView类型--%>
    <h3>测试返回值为ModelAndView类型</h3>
    <a href="user/testModelAndView">testModelAndView</a>
    <br><hr>


    <%--使用关键字的方式进行转发或者重定向--%>
    <h3>使用关键字的方式进行转发或者重定向</h3>
    <a href="user/testForwardOrRedirect">testForwardOrRedirect</a>
    <br><hr>


    <%--模拟异步请求--%>
    <button id="btn">发送ajax的请求</button>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <h3>传统文件上传</h3>
    <form action="user/fileUpload1" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br>
        <input type="submit" value="上传">
    </form>
    <br><hr>



    <h3>SpringMVC文件上传</h3>
    <form action="user/fileUpload2" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br>
        <input type="submit" value="上传">
    </form>
    <br><hr>


    <h3>SpringMVC跨服务器文件上传</h3>
    <form action="user/fileUpload3" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br>
        <input type="submit" value="上传">
    </form>
    <br><hr>

</body>
</html>

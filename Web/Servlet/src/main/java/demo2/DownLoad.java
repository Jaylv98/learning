package demo2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;


/*
*       文件下载案例：
*               分析：
*                   1.超链接指向的资源如果能被浏览器解析，则在浏览器中展示，如果不能解析，则弹出提示框，不满足需求
*                   2.任何资源都必须弹出下载提示框
*                   3.使用响应头设置资源的打开方式
*                       content-disposition:attachment;filename=xxx
*
*               步骤：
*                   1.定义页面，编辑超链接href属性，指向Servlet，传递资源名称filename
*                   2.定义Servlet
*                       获取文件名称
*                       使用字节输入流加载文件进内存
*                       指定response的响应头：content-disposition:attachment;filename=xxx
*                       将数据写出到response输出流
*
* */
@WebServlet(name = "DownLoad", value = "/DownLoad")
public class DownLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求参数，文件名称
        String filename = request.getParameter("filename");

        //使用字节输入流加载文件进内存
        //找到文件服务器路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);
        //调用字节流关联
        FileInputStream fis=new FileInputStream("realPath");

        //设置response的响应头
        //设置响应头类型：content-type
        String mimeType = context.getMimeType(filename);//获取文件MIME的类型
        response.setHeader("content-type",mimeType);
        //设置响应头打开方式:content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);

        //将输入流的数据写到输出流
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff=new byte[1024*8];
        int len=0;
        while((len=fis.read(buff))!=-1){
            sos.write(buff,0,len);
        }

        fis.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}

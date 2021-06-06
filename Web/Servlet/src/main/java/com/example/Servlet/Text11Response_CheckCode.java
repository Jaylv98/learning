package com.example.Servlet;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


//验证码案例
@WebServlet(name = "Text11Response_CheckCode", value = "/Text11Response_CheckCode")
public class Text11Response_CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width=100;
        int height=50;

        //创建对象，在内存中画图（验证码图片对象）
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //美化图片
        //填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.pink);//设置画笔颜色
        g.fillRect(0,0,width,height);
        //画边框
        g.setColor(Color.blue);
        g.drawRect(0,0,width-1,height-1);
        //写验证码
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random ran=new Random();//生成随机角标
        for(int i=1;i<=4;i++){
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);//随机字符
            g.drawString(ch+"",width/5*i,height/2);
        }
        //添加干扰线
        g.setColor(Color.green);
        //随机生成坐标点
        for (int i=0;i<10;i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }


        //将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}

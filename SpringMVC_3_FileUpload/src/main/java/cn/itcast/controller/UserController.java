package cn.itcast.controller;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    //传统方式文件上传
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("fileUpload1方法已执行");
        //使用fileupload组件完成文件上传
        //指定上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file=new File(path);
        if(!file.exists()){
            //不存在，创建该文件夹
            file.mkdirs();
        }

        //解析request对象，获取文件上传项
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        //遍历
        for(FileItem item:items){
            //判断，当前item对象是否是上传文件项
            if(item.isFormField()){
                //说明是一个普通表单项
                System.out.println("不是一个上传文件项");
            }else{
                //说明是一个上传文件项
                //获取文件上传名称
                String fileName = item.getName();
                //将文件的名称设置为唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName=uuid+"_"+fileName;
                //完成文件上传
                item.write(new File(path,fileName));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }



    //SpringMVC文件上传
    @RequestMapping("fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload /*名字需与表单名字相同*/) throws Exception {
        System.out.println("fileUpload2方法已执行");
        //使用fileupload组件完成文件上传
        //指定上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file=new File(path);
        if(!file.exists()){
            //不存在，创建该文件夹
            file.mkdirs();
        }

        //说明是一个上传文件项
        //获取文件上传名称
        String fileName = upload.getOriginalFilename();
        //将文件的名称设置为唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName=uuid+"_"+fileName;
        //完成文件上传
       upload.transferTo(new File(path,fileName));
        return "success";
    }




    //SpringMVC跨服务器文件上传
    @RequestMapping("fileUpload3")
    public String fileUpload3( MultipartFile upload /*名字需与表单名字相同*/) throws Exception {
        System.out.println("fileUpload3方法已执行");

        //定义上传文件服务器路径
        String path="http://localhost:9090/uploads/";

        //说明是一个上传文件项
        //获取文件上传名称
        String fileName = upload.getOriginalFilename();
        //将文件的名称设置为唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName=uuid+"_"+fileName;
        //跨服务器上传
        //创建客户端对象
        Client client = Client.create();
        //和图片服务器进行连接
        WebResource resource = client.resource(path + fileName);
        //上传文件
        resource.put(upload.getBytes());

        return "success";
    }

}

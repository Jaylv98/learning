package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import jdk.nashorn.internal.parser.TokenStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//测试mybatis的CRUD操作
public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;


    //封装准备步骤
    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        //读取配置文件
       in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //使用工厂生产SqlSession对象
        session = factory.openSession();
        //使用SqlSession创建Dao的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    //释放资源
    @After//用于在测试方法执行之后执行
    public void destory() throws Exception{

        //提交事务
        session.commit();

        //释放资源
        session.close();
        in.close();
    }


    //测试查询所有的操作
    @Test
    public void testFindAll(){
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }

    }

    //测试保存用户信息的操作
    @Test
    public void testSave() throws Exception{
        User user=new User();
        user.setUsername("王五");
        user.setAddress("上海市杨浦区");
        user.setSex("女");
        user.setBirthday(new Date());

        System.out.println("保存操作之前"+user);
        userDao.saveUser(user);
        System.out.println("保存操作之后"+user);
    }


    //测试修改用户信息的操作
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(49);
        user.setUsername("李四");
        user.setAddress("北京市朝阳区");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    //测试删除用户信息的操作
    @Test
    public void testDelete(){

        userDao.deleteUser(49);
    }

    //测试查询单个用户信息的操作
    @Test
    public void testFindOne(){

        User user=userDao.findById(50);
        System.out.println(user);

    }

    //测试模糊查询的操作
    @Test
    public void testFindByName(){
        List<User> users= userDao.findByName("%王%");
        //List<User> users= userDao.findByName("王");
        for(User user:users){
            System.out.println(user);
        }
    }

    //测试查询总用户数的操作
    @Test
    public void testFindTotal(){
        int sum=userDao.findTotal();
        System.out.println("总用户数为："+sum);
    }


    //测试使用QueryVo作为查询条件
    @Test
    public void testFindByVo(){
        QueryVo vo=new QueryVo();
        User user=new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users= userDao.findUserByVo(vo);
        //List<User> users= userDao.findByName("王");
        for(User u:users){
            System.out.println(u);
        }
    }


    //测试查询所有的操作
    @Test
    public void testFindByCondition(){
        User u=new User();
        u.setUsername("老王");
        u.setSex("男");

        //使用代理对象执行方法
        List<User> users = userDao.findUserByCondition(u);
        for(User user:users){
            System.out.println(user);
        }

    }


    //测试foreach标签的使用
    @Test
    public void testFindInIds(){
     QueryVo vo=new QueryVo();
     List<Integer> list=new ArrayList<Integer>();
     list.add(41);
     list.add(42);
     list.add(43);
     vo.setIds(list);

        //使用代理对象执行方法
        List<User> users = userDao.findUserInIds(vo);
        for(User user:users){
            System.out.println(user);
        }

    }
}

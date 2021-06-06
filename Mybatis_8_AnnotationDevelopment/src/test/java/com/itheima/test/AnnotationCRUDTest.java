package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCRUDTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws Exception{
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        factory=new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user=new User();
        user.setUsername("Mybatis Annotation");
        user.setAddress("上海市");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user =new User();
        user.setId(52);
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("福建省");
        user.setUsername("李四二号");

        userDao.updateUser(user);
    }

    @Test
    public void testDelete(){

        userDao.deleteUser(53);
    }


    @Test
    public void testFindOne(){

       User user= userDao.findById(52);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){

        List<User> users=userDao.findByName("王");
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotalUser() {

        int count=userDao.findTotalUser();
        System.out.println("总用户数为："+count+"人");

    }
}

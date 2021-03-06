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

public class AnnotationMappingTest {

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
   public void testFindAll(){
       List<User> users = userDao.findAll();
       for (User user :users) {
           System.out.println(user);
       }
   }

    @Test
    public void testFindOne(){

       User user= userDao.findById(52);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){

        List<User> users=userDao.findByName("%王%");
        for (User user :users) {
            System.out.println(user);
        }
    }


}

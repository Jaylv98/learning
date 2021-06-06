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
import java.util.List;


/*

            什么是延迟加载
                    在真正使用数据时才发起查询，不用的时候不查询。按需加载（懒加载）
            什么是立即加载
                    不管用不用，只要一调用方法，马上发起查询。
            在对应的四种表关系中：一对多，多对一，一对一，多对多
                    一对多，多对多：通常情况下我们都是采用延迟加载。
                    多对一，一对一：通常情况下我们都是采用立即加载。

*/



//mybatis的延迟加载（懒加载）
public class UserTest {

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


    //测试查询所有
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        /*for (User user : users) {
            System.out.println("每个用户的信息");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }*/

   /*

        //已经实现延迟加载  只执行了查询账户信息，并没有执行查询用户信息，
        Preparing: select * from user ;
        Parameters:
             Total: 9
*/
    }



}

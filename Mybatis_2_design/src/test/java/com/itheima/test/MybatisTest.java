package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.mybatis.io.Resources;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

//入门案例
public class MybatisTest {
    public static void main(String[] args) throws Exception{

        //读取配置文件
        InputStream in= Resources.getResourceAsStream("xml/SqlMapConfig.xml");

        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);

        //使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();

        //使用SqlSession创建Dao的代理对象
        UserDao userDao = session.getMapper(UserDao.class);

        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }

        //释放资源
        session.close();
        in.close();
    }
}

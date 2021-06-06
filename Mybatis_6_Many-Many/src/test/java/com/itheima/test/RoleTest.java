package com.itheima.test;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
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


//多对多表的查询
public class RoleTest {

    private InputStream in;
    private SqlSession session;
    private RoleDao roleDao;


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
        roleDao = session.getMapper(RoleDao.class);
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
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("每个角色的信息");
            System.out.println(role);
            System.out.println(role.getUsers());
        }

    }



}

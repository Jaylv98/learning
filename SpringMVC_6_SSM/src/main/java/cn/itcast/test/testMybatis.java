package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class testMybatis {

    private InputStream is;
    private SqlSessionFactory factory;
    private SqlSession session;
    private AccountDao dao;


    @Before
    public  void init()throws Exception{
        //加载mybatis配置文件
        is = Resources.getResourceAsStream("applicationContext.xml");
        //创建SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(is);
        //使用工厂创建SqlSession对象
        session = factory.openSession();
        //获取代理对象
        dao = session.getMapper(AccountDao.class);
    }


    @After
    public void destroy() throws Exception{
        //释放资源
        session.close();
        is.close();
    }


    @Test
    //查询所有用户信息
    public void run1() {
        //查询所有数据
        List<Account> list = dao.findAll();
        for(Account account:list){
            System.out.println(account);
        }
    }


    @Test
    //保存用户信息
    public void run2(){
        Account account=new Account();
        account.setName("李四");
        account.setMoney(500.2);

        dao.saveAccount(account);
        //提交事务
        session.commit();
    }
}

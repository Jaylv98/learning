package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory=factory;
    }

    @Override
    public List<User> findAll() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.itheima.dao.UserDao.findAll");//参数就是能获取到配置信息的key
        //释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.itheima.dao.UserDao.saveUser",user);
        //提交事务
        session.commit();
        session.close();

    }

    @Override
    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("com.itheima.dao.UserDao.updateUser",user);
        //提交事务
        session.commit();
        session.close();

    }

    @Override
    public void deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        session.update("com.itheima.dao.UserDao.deleteUser",userId);
        //提交事务
        session.commit();
        session.close();

    }

    @Override
    public User findById(Integer userId) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询单个
        User user = session.selectOne("com.itheima.dao.UserDao.findById",userId);
        //释放资源
        session.close();
        return user;

    }

    @Override
    public List<User> findByName(String username) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询单个
        List<User> users= session.selectList("com.itheima.dao.UserDao.findByName",username);
        //释放资源
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询单个
        Integer count = session.selectOne("com.itheima.dao.UserDao.findTotal");
        //释放资源
        session.close();
        return count;
    }
}

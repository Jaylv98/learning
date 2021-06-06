package dao.impl;

import dao.UserDao;
import domin.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库

        //定义sql
        String sql="select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
/*
        String sql="select * from user where username= ? and password=?";
        User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
        return user;*/

        String sql="select * from user where username=?";
        User user =template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        if(!Objects.equals(user.getPassword(),password)){
            return null;
        }else{
            return user;
        }

    }

    @Override
    public void add(User user) {
        //定义sql语句
        String sql="insert into user values(null,?,?,?,?,?,?,null,null)";
        //执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delete(int id) {
        //定义sql
        String sql="delete from user where id=?";
        //执行sql
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        String sql="select * from user where id=?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
        String sql="update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount() {
        //定义模板初始化sql
        String sql="select count(*) from user ";

        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<User> findByPage(int start, int rows) {
        String sql="select * from user limit ?,?";

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);
    }


}

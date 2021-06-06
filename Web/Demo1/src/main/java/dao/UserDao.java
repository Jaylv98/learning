package dao;

import domin.User;

import java.util.List;
import java.util.Map;

//用户操作的DAO
public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username,String password);

    void add(User user);


    void delete(int parseInt);

    User findById(int parseInt);

    void update(User user);
    //查询总记录数
    int findTotalCount();
    //分页查询每页记录
    List<User> findByPage(int start, int rows);
}

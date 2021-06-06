package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    //根据用户名查询用户信息
    public User findByUsername(String username);

    //用户保存
    public void save(User user);

    //根据激活码查询用户对象
    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}

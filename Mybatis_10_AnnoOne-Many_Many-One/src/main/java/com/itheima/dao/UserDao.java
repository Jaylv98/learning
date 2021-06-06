package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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


//在mybatis中，针对CRUD一共有四个注解 @Select @Insert @Update @Delete
//使用注解的方式给实体类起别名
public interface UserDao {

    //查询所有
    @Select("select * from user")
    @Results(id = "userMap",value={
            @Result(id = true,property = "userId",column = "id"),//主键id
            @Result(property = "userName",column = "username"),
            @Result(property = "userSex",column = "sex"),
            @Result(property = "userAddress",column = "address"),
            @Result(property = "userBirthday",column = "birthday"),
             /* property:需要封装的属性；
            column:通过哪个列名寻找
            one:xx对一的多表关系
            select:指向哪个方法
            fetchType:加载类型（延迟加载或立即加载）
            */
            @Result(property = "accounts",column = "id",
                    many = @Many(select="com.itheima.dao.AccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })



    List<User> findAll();


    //查询单个用户信息
    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    User findById(Integer userId);

    //根据用户名称模糊查询
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findByName(String username);


}

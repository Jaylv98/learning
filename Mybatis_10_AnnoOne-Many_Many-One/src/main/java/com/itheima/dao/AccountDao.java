package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.junit.jupiter.api.Test;

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

public interface AccountDao {

    //查询所有账户，并且获取每个账户所属的用户信息
    @Select("select * from  account")
    @Results(id = "accountMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "money",column = "money"),
           /* property:需要封装的属性；
            column:通过哪个列名寻找
            one:xx对一的多表关系
            select:指向哪个方法
            fetchType:加载类型（延迟加载或立即加载）
            */
            @Result(property = "user",column = "uid",
                    one = @One(select="com.itheima.dao.UserDao.findById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();


    //根据用户id查询账户信息
    @Select("select * from account where uid=#{userId}")
    List<Account> findAccountByUid(Integer userId);
}

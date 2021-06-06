package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {


    @Select("select * from role")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "users",column = "id", javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.UserDao.findById"))
    })
    public List<Role> findAll();

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(Integer userId);


    @Insert("insert into role (id,roleName,roleDesc) values (#{id},#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from role where id=#{roleId}")
    Role findById(Integer roleId);


    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(Integer roleId);


    @Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId") Integer permissionId, @Param("roleId") Integer roleId);
}

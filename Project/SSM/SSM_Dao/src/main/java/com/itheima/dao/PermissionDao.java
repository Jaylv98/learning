package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//权限表
public interface PermissionDao {


    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findPermissionByRoleId(Integer roleId);


    @Select("select * from permission")
    public List<Permission> findAll();


    @Insert("insert into permission (id,permissionName,url) values (#{id},#{permissionName},#{url})")
    void save(Permission permission);
}

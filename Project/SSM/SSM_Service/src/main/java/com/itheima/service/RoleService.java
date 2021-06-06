package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    //查询所有角色信息
    List<Role> findAll(int page,int size);

    void save(Role role);


    Role findById(Integer roleId);


    List<Permission> findOtherPermission(Integer roleId);


    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}

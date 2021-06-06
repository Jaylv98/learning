package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {

    //查询所有权限
    List<Permission> findAll(int page,int size);

    //保存权限信息
    void save(Permission permission);
}

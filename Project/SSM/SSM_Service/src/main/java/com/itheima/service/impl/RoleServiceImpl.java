package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> findAll(int page, int size) {
        /*
                 PageHelper是mybatis的一个分页展示插件
                    pageNum:页码值；
                    pageSize:每页显示的条数
        */
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(Integer roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (Integer permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}

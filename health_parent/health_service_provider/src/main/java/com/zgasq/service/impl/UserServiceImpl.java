package com.zgasq.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.zgasq.dao.PermissionDao;
import com.zgasq.dao.RoleDao;
import com.zgasq.dao.UserDao;
import com.zgasq.pojo.Permission;
import com.zgasq.pojo.Role;
import com.zgasq.pojo.User;
import com.zgasq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissonDao;
    @Override
    public User findByUsername(String username) {
        User user =userDao.findByUsername(username);
        if(user ==null){
            return null;
        }
        Integer userId =user.getId();
        Set<Role> roles = roleDao.findByUserId(userId);
        for(Role role:roles){
            Integer roleId =role.getId();
            Set<Permission> permissions = permissonDao.findByRoleId(roleId);
            role.setPermissions(permissions);
        }

        user.setRoles(roles);
        return user;
    }
}

package com.example.demo.service.impl;

import com.example.demo.Entry.SysPermission;
import com.example.demo.Entry.SysRole;
import com.example.demo.Entry.SysUser;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.service.LoginService;
import com.example.demo.unitl.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/10/23.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //添加用户
    @Override
    public SysUser addUser(Map<String, Object> map) {
        SysUser user = new SysUser();
        user.setName(map.get("username").toString());
        user.setPassword(MD5Util.md5Password(map.get("password").toString()));
        user.setCreate_time(System.currentTimeMillis()/1000);
        user.setPhone(Long.valueOf(map.get("phone").toString()));
        user.setState(0);
        user.setUpdate_time(System.currentTimeMillis()/1000);
//        user.setId_card_num(map.get("id_card_num").toString());
        userRepository.save(user);
        user=userRepository.findByPhone(Long.valueOf(map.get("phone").toString()));
        SysRole role=new SysRole();
        role.setRoleName(map.get("role").toString());
        role.setUser(user);

        SysPermission permission1 = new SysPermission();
        permission1.setPermission("create");
        permission1.setRole(role);
        SysPermission permission2 = new SysPermission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<SysPermission> permissions = new ArrayList<SysPermission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return user;
    }

    //添加角色
    @Override
    public SysRole addRole(Map<String, Object> map) {
        SysUser user = userRepository.findOne(Long.valueOf(map.get("userId").toString()));
        SysRole role = new SysRole();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user);
        SysPermission permission1 = new SysPermission();
        permission1.setPermission("create");
        permission1.setRole(role);
        SysPermission permission2 = new SysPermission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<SysPermission> permissions = new ArrayList<SysPermission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }

    //查询用户通过用户名
    @Override
    public SysUser findByName(String name) {
        return userRepository.findByName(name);
    }
}

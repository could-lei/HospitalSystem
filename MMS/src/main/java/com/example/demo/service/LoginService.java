package com.example.demo.service;

import com.example.demo.Entry.SysRole;
import com.example.demo.Entry.SysUser;

import java.util.Map;

/**
 * Created by next on 2018/10/23.
 */
public interface LoginService {
    public SysRole addRole(Map<String, Object> map);
    public SysUser findByName(String name);
    public SysUser addUser(Map<String, Object> map);

    }

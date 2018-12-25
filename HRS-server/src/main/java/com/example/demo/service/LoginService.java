package com.example.demo.service;

import com.example.demo.Entry.SysRole;
import com.example.demo.Entry.SysUser;

import java.util.Map;

/**
 * Created by next on 2018/10/23.
 * @author next
 */
public interface LoginService {
    /**
     *
     *add data
     * @param map
     * @return
     */
    public SysRole addRole(Map<String, Object> map);

    /**
     * find by name
     * @param name
     * @return
     */
    public SysUser findByName(String name);

    /**
     * add user data
     * @param map
     * @return
     */
    public SysUser addUser(Map<String, Object> map);

    }

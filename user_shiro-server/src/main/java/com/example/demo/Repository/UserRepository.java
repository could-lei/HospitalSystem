package com.example.demo.Repository;

import com.example.demo.Entry.SysUser;

/**
 * Created by next on 2018/10/23.
 */
public interface UserRepository extends BaseRepository<SysUser,Long>{
    SysUser findByName(String name);
    SysUser findByPhone(Long phone);
}

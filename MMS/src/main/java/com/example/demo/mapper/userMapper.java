package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.user;
import org.apache.ibatis.annotations.Param;

public interface userMapper {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("idCardNum") String idCardNum);

    int insert(user record);

    user selectByPrimaryKey(@Param("id") Long id, @Param("idCardNum") String idCardNum);

    user selectById(Long id);

    List<user> selectAll();

    int updateByPrimaryKey(user record);

    user findByName(String name);
}
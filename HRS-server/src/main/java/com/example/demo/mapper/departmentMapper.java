package com.example.demo.mapper;

import com.example.demo.model.department;

import java.util.List;

public interface departmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(department record);

    department selectByPrimaryKey(Integer id);

    List<department> selectAll();

    int updateByPrimaryKey(department record);
}
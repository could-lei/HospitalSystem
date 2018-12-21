package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.department;

public interface departmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(department record);

    department selectByPrimaryKey(Integer id);

    List<department> selectAll();

    int updateByPrimaryKey(department record);
}
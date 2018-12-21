package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.file;

public interface fileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(file record);

    file selectByPrimaryKey(Integer id);

    List<file> selectAll();

    int updateByPrimaryKey(file record);
}
package com.example.demo.mapper;

import com.example.demo.model.file;

import java.util.List;

public interface fileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(file record);

    file selectByPrimaryKey(Integer id);

    List<file> selectAll();

    int updateByPrimaryKey(file record);
}
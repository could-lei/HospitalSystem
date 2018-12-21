package com.example.demo.mapper;

import com.example.demo.model.fda;

import java.util.List;

public interface fdaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(fda record);

    fda selectByPrimaryKey(Integer id);

    List<fda> selectAll();

    int updateByPrimaryKey(fda record);
}
package com.example.demo.mapper;

import com.example.demo.model.porperty;

import java.util.List;

public interface porpertyMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(porperty record);

    porperty selectByPrimaryKey(Long uid);

    List<porperty> selectAll();

    int updateByPrimaryKey(porperty record);
}
package com.example.demo.mapper;

import com.example.demo.model.equipment;

import java.util.List;

public interface equipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(equipment record);

    equipment selectByPrimaryKey(Integer id);

    List<equipment> selectAll();

    int updateByPrimaryKey(equipment record);
}
package com.example.demo.mapper;

import com.example.demo.model.equipment;

import java.util.List;

public interface equipmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(equipment record);

    equipment selectByPrimaryKey(Long id);

    List<equipment> selectAll();

    int updateByPrimaryKey(equipment record);
}
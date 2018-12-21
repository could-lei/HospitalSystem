package com.example.demo.mapper;

import com.example.demo.model.equipment_use_history;

import java.util.List;

public interface equipment_use_historyMapper {
    int deleteByPrimaryKey(Long eid);

    int insert(equipment_use_history record);

    equipment_use_history selectByPrimaryKey(Long eid);

    List<equipment_use_history> selectAll();

    int updateByPrimaryKey(equipment_use_history record);
}
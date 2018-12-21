package com.example.demo.mapper;

import com.example.demo.model.equipment_use_history;

import java.util.List;

public interface equipment_use_historyMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(equipment_use_history record);

    equipment_use_history selectByPrimaryKey(Integer eid);

    List<equipment_use_history> selectAll();

    int updateByPrimaryKey(equipment_use_history record);
}
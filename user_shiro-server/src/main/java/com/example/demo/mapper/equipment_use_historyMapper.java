package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.equipment_use_history;

public interface equipment_use_historyMapper {
    int deleteByPrimaryKey(Long eid);

    int insert(equipment_use_history record);

    equipment_use_history selectByPrimaryKey(Long eid);

    List<equipment_use_history> selectAll();

    int updateByPrimaryKey(equipment_use_history record);
}
package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.equipment;

public interface equipmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(equipment record);

    equipment selectByPrimaryKey(Long id);

    List<equipment> selectAll();

    int updateByPrimaryKey(equipment record);
}
package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.porperty;

public interface porpertyMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(porperty record);

    porperty selectByPrimaryKey(Long uid);

    List<porperty> selectAll();

    int updateByPrimaryKey(porperty record);
}
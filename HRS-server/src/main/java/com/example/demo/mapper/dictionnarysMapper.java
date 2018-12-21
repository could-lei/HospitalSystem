package com.example.demo.mapper;

import com.example.demo.model.dictionnarys;

import java.util.List;

public interface dictionnarysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(dictionnarys record);

    dictionnarys selectByPrimaryKey(Integer id);

    List<dictionnarys> selectAll();

    int updateByPrimaryKey(dictionnarys record);
}
package com.example.demo.mapper;

import com.example.demo.model.items;

import java.util.List;

public interface itemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(items record);

    items selectByPrimaryKey(Integer id);

    List<items> selectAll();

    int updateByPrimaryKey(items record);
}
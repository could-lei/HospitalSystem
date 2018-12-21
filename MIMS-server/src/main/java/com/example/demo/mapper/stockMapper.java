package com.example.demo.mapper;

import com.example.demo.model.stock;

import java.util.List;

public interface stockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(stock record);

    stock selectByPrimaryKey(Integer id);

    List<stock> selectAll();

    int updateByPrimaryKey(stock record);
}
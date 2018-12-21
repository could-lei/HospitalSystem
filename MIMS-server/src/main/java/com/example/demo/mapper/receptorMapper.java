package com.example.demo.mapper;

import com.example.demo.model.receptor;

import java.util.List;

public interface receptorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(receptor record);

    receptor selectByPrimaryKey(Long id);

    List<receptor> selectAll();

    int updateByPrimaryKey(receptor record);
}
package com.example.demo.mapper;

import com.example.demo.model.receptor;

import java.util.List;

public interface receptorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(receptor record);

    receptor selectByPrimaryKey(Long id);

    List<receptor> selectAll();

    List<receptor> selectByMerdiateId(Long medicare_id);

    int updateByPrimaryKey(receptor record);

    receptor selectByMid(Long mid);

//    int updateBy()
}
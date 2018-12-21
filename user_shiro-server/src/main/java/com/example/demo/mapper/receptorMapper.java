package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.receptor;

public interface receptorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(receptor record);

    receptor selectByPrimaryKey(Long id);

    List<receptor> selectAll();

    int updateByPrimaryKey(receptor record);
}
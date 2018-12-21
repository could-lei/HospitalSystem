package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.fda;

public interface fdaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(fda record);

    fda selectByPrimaryKey(Integer id);

    List<fda> selectAll();

    int updateByPrimaryKey(fda record);
}
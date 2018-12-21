package com.example.demo.mapper;

import java.util.List;

import com.example.demo.model.check;
import org.apache.ibatis.annotations.Param;

public interface checkMapper {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("rId") Long rId, @Param("dId") Long dId);

    int insert(check record);

    check selectByPrimaryKey(@Param("id") Long id, @Param("rId") Long rId, @Param("dId") Long dId);

    List<check> selectAll();

    int finishCheck(Long rId);

    List<check> selectByMid(Long id);

    int updateByPrimaryKey(check record);
}
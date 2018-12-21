package com.example.demo.mapper;

import com.example.demo.model.receptor;

import java.util.List;

public interface receptorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(receptor record);

    receptor selectByPrimaryKey(Long id);

    receptor selectByMedicareId(Long medicareId);

    List<receptor> selectAll();

    int updateByPrimaryKey(receptor record);
    int closeReceptor(Long uId);
    int finishReceptor(Long id);
    receptor selectByUid(Long uId);
}
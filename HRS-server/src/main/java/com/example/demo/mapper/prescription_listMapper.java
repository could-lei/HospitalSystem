package com.example.demo.mapper;

import com.example.demo.Entry.prise;
import com.example.demo.model.prescription_list;

import java.util.List;

public interface prescription_listMapper {
    int deleteByPrimaryKey(Long id);

    int insert(prescription_list record);

    prescription_list selectByPrimaryKey(Long id);

    List<prescription_list> selectAll();

    int updateByPrimaryKey(prescription_list record);

    List<prise> accout(Long medicare);
}
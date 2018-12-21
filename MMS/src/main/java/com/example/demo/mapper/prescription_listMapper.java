package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.prescription_list;

public interface prescription_listMapper {
    int deleteByPrimaryKey(Long id);

    int insert(prescription_list record);

    prescription_list selectByPrimaryKey(Long id);

    List<prescription_list> selectAll();

    int updateByPrimaryKey(prescription_list record);

    List<prescription_list> selectByRid(Long rid);
}
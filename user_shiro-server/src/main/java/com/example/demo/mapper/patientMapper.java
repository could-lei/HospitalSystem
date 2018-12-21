package com.example.demo.mapper;

import java.util.List;
import com.example.demo.model.patient;

public interface patientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(patient record);

    patient selectByPrimaryKey(Long id);

    List<patient> selectAll();

    int updateByPrimaryKey(patient record);
}
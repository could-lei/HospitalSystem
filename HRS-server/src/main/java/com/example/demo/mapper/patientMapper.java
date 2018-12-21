package com.example.demo.mapper;

import com.example.demo.model.patient;
import java.util.List;

public interface patientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(patient record);

    patient selectByPrimaryKey(Long id);

    patient selectByMedicareId(Long id);

    List<patient> selectAll();

    int updateByPrimaryKey(patient record);
}
package com.example.demo.mapper;

import com.example.demo.model.patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface patientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(patient record);

    patient selectByPrimaryKey(Long id);

    patient selectByMedicareId(Long id);

    List<patient> selectAll();

    int updateByPrimaryKey(patient record);
    patient check(@Param("phone")Long phone, @Param("idcard")String idcard);
}
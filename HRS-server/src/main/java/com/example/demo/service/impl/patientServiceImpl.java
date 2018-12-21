package com.example.demo.service.impl;

import com.example.demo.mapper.patientMapper;
import com.example.demo.model.patient;
import com.example.demo.service.patientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by next on 2018/11/29.
 */
@Service
public class patientServiceImpl implements patientService {
    @Resource
    private patientMapper patientMapper;
    @Override
    public int addPatient(patient patient) {
        return patientMapper.insert(patient);
    }

    @Override
    public patient searchPatient(String medicareId) {
        return patientMapper.selectByMedicareId(Long.valueOf(medicareId));
    }
}

package com.example.demo.service.impl;

import com.example.demo.mapper.checkMapper;
import com.example.demo.mapper.patientMapper;
import com.example.demo.mapper.receptorMapper;
import com.example.demo.model.patient;
import com.example.demo.model.receptor;
import com.example.demo.service.checkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by next on 2018/12/16.
 */
@Service
public class checkServiceImpl implements checkService {
    @Resource
    private checkMapper checkMapper;
    @Resource
    private patientMapper patientMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Override
    public void finishCheck(Long medicareId) {
        receptor r=receptorMapper.selectByMedicareId(medicareId);
        patient x= patientMapper.selectByMedicareId(medicareId);
        checkMapper.finishCheck(r.getId());
    }
}

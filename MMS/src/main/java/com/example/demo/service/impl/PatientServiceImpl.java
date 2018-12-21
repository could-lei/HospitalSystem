package com.example.demo.service.impl;

import com.example.demo.enums.PatientResponse;
import com.example.demo.mapper.patientMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.model.patient;
import com.example.demo.model.user;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by next on 2018/12/20.
 * @author next
 */
@Service
public class PatientServiceImpl implements PatientService{
    @Resource
    private patientMapper patientMapper;
    @Resource
    private userMapper userMapper;
    @Override
    public patient searchPatient(int type, String target) {
        patient p=new patient();
        switch (type){
            case 1:p=patientMapper.selectByMedicareId(Long.valueOf(target));
            break;
            case 2:p=patientMapper.selectByIdCard(target);
            break;
            case 3:p=patientMapper.selectByPhone(Long.valueOf(target));
            break;
            default:p=new patient();
        }
        return p;
    }

    @Override
    public PatientResponse Serialize(patient patient) {
        PatientResponse p=new PatientResponse();
        p.setAge(18);
        p.setDesc("无过敏");
        p.setGender(patient.getSex()==0?"女":"男");
        p.setId(patient.getId());
        p.setMid(patient.getMedicare()+"");
        p.setName(patient.getName());
        p.setPlace(patient.getBirthplace());
        return p;
    }

    @Override
    public user FindUserByName(String name) {
        return userMapper.findByName(name);
    }
}

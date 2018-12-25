package com.example.demo.service.impl;

import com.example.demo.mapper.patientMapper;
import com.example.demo.model.patient;
import com.example.demo.service.patientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by next on 2018/11/29.
 */
@Service
public class patientServiceImpl implements patientService {
    @Resource
    private patientMapper patientMapper;
    @Override
    public int addPatient(patient patient) {
        patient.setMedicare(Long.valueOf(game(11)));
        return patientMapper.insert(patient);
    }

    @Override
    public patient searchPatient(String medicareId) {
        return patientMapper.selectByMedicareId(Long.valueOf(medicareId));
    }

    @Override
    public patient searchPatientById(String id) {
        return patientMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @Override
    public boolean check(String idcard, long phone) {
        if (patientMapper.check(phone,idcard)==null){
            return true;
        }else {
            return false;
        }
    }

    public static String game(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }
}

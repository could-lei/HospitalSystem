package com.example.demo.service;

import com.example.demo.Entry.SysUser;
import com.example.demo.enums.PatientResponse;
import com.example.demo.model.patient;
import com.example.demo.model.user;

/**
 * Created by next on 2018/12/20.
 * @author next
 */
public interface PatientService {
    /**
     * fetch data by target type
    *@param target  数据
     *@param type 类型 1 医疗卡号查询 2 身份证号查询 3 手机号查询
     * @return patient
    */
    public patient searchPatient(int type,String target);
    /**
     * serialize patient
     * @param patient 规格化对象
     * @return PatientResponse
     */
    public PatientResponse Serialize(patient patient);
    /**
     * serialize patient
     * @param name 名字
     * @return user
     */
    public user FindUserByName(String name);
}

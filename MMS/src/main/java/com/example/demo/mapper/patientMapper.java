package com.example.demo.mapper;

import com.example.demo.model.patient;

import java.util.List;

public interface patientMapper {
    /**
     * delete data
     *@param id id号
     * @return int
     */
    int deleteByPrimaryKey(Long id);
    /**
     * insert data
     *@param record patient对象
     * @return int
     */
    int insert(patient record);
    /**
     * fetch data by id
     *@param id
     * @return patient
     */
    patient selectByPrimaryKey(Long id);
    /**
     * fetch data by medicare
     *@param id 医疗卡号
     * @return patient
     */
    patient selectByMedicareId(Long id);
    /**
     * fetch data by phone
     * @param phone 手机号
     * @return patient
     */
    patient selectByPhone(Long phone);

    /**
     * fetch data by idCard
     * @param IdCard
     * @return
     */
    patient selectByIdCard(String IdCard);
    /**
     * fetch all of data
     *
     * @return List<patient>
     */
    List<patient> selectAll();
    /**
     * update data by patient 对象
     *@param record patient 对象
     * @return int
     */
    int updateByPrimaryKey(patient record);
}
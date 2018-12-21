package com.example.demo.service;

import com.example.demo.model.patient;

/**
 * Created by next on 2018/11/29.
 */
public interface patientService {
    public int addPatient(patient patient);
    public patient searchPatient(String medicareId);

}

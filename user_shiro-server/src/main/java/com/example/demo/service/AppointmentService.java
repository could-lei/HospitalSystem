package com.example.demo.service;

import com.example.demo.model.patient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by next on 2018/12/6.
 */
@Service
public class AppointmentService {
    @Cacheable(value = "sys_patient",key = "#name")
    public LinkedList<patient> save(String name){
        return new LinkedList<patient>();
    }
    @CacheEvict(value = "sys_patient",key = "#name")
    public LinkedList<patient> delete(String name,LinkedList<patient> appointment){
        return new LinkedList<patient>();
    }
    @CachePut(value = "sys_patient",key = "#name")
    public LinkedList<patient> update(String name,LinkedList<patient> appointment){
        return appointment;
    }
}

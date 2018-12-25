package com.example.demo.service;

import com.example.demo.model.patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by next on 2018/12/6.
 * @author next
 */
@Service
public class AppointmentService {
    /**
     * insert data
     * @param name
     * @return
     */
    @Cacheable(value = "sys_patient",key = "#name")
    public LinkedList<patient> save(String name){
        return new LinkedList<patient>();
    }

    /**
     * delete by name
     * @param name
     * @param appointment
     * @return
     */
    @CacheEvict(value = "sys_patient",key = "#name")
    public LinkedList<patient> delete(String name,LinkedList<patient> appointment){
        return new LinkedList<patient>();
    }

    /**
     * update by name
     * @param name
     * @param appointment
     * @return
     */
    @CachePut(value = "sys_patient",key = "#name")
    public LinkedList<patient> update(String name,LinkedList<patient> appointment){
        return appointment;
    }
}

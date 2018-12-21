package com.example.demo.Repository;

import com.example.demo.model.patient;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

/**
 * Created by next on 2018/12/5.
 */
@CacheConfig(cacheNames = "users")
public interface AppointmentRepository extends JpaRepository<LinkedList<patient>,Long>{
    @Cacheable(key = "#name")
    LinkedList<patient> findByName(String name);
    @CachePut(key = "#name")
    LinkedList<patient> save(String name, LinkedList<patient> appointment);
}

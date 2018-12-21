package com.example.demo.service;

import com.example.demo.model.department;

import java.util.List;

/**
 * Created by next on 2018/11/30.
 */
public interface departmentService {
    public List<department> get_departments();
    public department search_department(Integer id);
    public void applyCheck(List<Long>ids,Long medicareId,int eId);
    public List<String> getProject(Long medicareId);
}

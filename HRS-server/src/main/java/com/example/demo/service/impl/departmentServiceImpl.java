package com.example.demo.service.impl;

import com.example.demo.mapper.departmentMapper;
import com.example.demo.model.department;
import com.example.demo.service.departmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by next on 2018/11/30.
 */
@Service
public class departmentServiceImpl implements departmentService {
    @Resource
    private departmentMapper departmentMapper;
    @Override
    public List<department> get_departments() {
        return departmentMapper.selectAll();
    }
}

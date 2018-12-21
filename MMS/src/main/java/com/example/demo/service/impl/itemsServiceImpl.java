package com.example.demo.service.impl;

import com.example.demo.mapper.departmentMapper;
import com.example.demo.mapper.itemsMapper;
import com.example.demo.model.department;
import com.example.demo.model.items;
import com.example.demo.service.itemsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by next on 2018/12/3.
 */
@Service
public class itemsServiceImpl implements itemsService {
    @Resource
    private itemsMapper itemsMapper;
    @Resource
    private departmentMapper departmentMapper;
    @Override
    public List<department> getAllItems() {
        return  departmentMapper.selectAll();
    }
}

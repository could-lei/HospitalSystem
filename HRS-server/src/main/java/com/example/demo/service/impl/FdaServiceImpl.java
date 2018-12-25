package com.example.demo.service.impl;

import com.example.demo.mapper.fdaMapper;
import com.example.demo.model.fda;
import com.example.demo.service.FdaService;

import javax.annotation.Resource;

/**
 * Created by next on 2018/12/23.
 */

public class FdaServiceImpl implements FdaService{
    @Resource
    private fdaMapper fdaMapper;
    @Override
    public fda findFdaById(Integer id) {
        return fdaMapper.selectByPrimaryKey(id);
    }
}

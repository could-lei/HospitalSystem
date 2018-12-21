package com.example.demo.service.impl;

import com.example.demo.mapper.itemsMapper;
import com.example.demo.model.items;
import com.example.demo.service.itemsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by next on 2018/12/3.
 */
@Service
public class itemsServiceImpl implements itemsService {
    @Resource
    private itemsMapper itemsMapper;
    @Override
    public List<items> getAllItems() {
        return  itemsMapper.selectAll();
    }
}

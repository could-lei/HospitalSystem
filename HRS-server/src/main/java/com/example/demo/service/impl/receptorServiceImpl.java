package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entry.prise;
import com.example.demo.mapper.prescription_listMapper;
import com.example.demo.mapper.receptorMapper;
import com.example.demo.mapper.stockMapper;
import com.example.demo.model.receptor;
import com.example.demo.service.receptorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by next on 2018/12/5.
 */
@Service
public class receptorServiceImpl implements receptorService {
    @Resource
    private prescription_listMapper prescription_listMapper;
    @Resource
    private stockMapper stockMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Override
    public List<receptor> getReceptors(Integer id) {
        return null;
    }

    @Override
    public void addReceptor() {

    }

    @Override
    public double account(Long medicare){
        List<prise> list=prescription_listMapper.accout(medicare);
        double price=0;
        for (prise p:list
             ) {
            price=p.getPrice()*p.getNum();
        }
        return price;
    }

    @Override
    public void charge(Long medicare) {
        List<receptor> res=receptorMapper.selectByMerdiateId(medicare);
        receptor f=res.get(0);
        f.setState(1);
        for (int i = 1; i < res.size(); i++) {
            res.get(i).setState(-1);
        }
        receptorMapper.updateByPrimaryKey(f);
    }
}

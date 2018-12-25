package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entry.prise;
import com.example.demo.enums.Detail;
import com.example.demo.enums.Item;
import com.example.demo.enums.OrderResponse;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.receptorService;
import com.example.demo.unitl.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/12/5.
 * @author next
 */
@Service
public class receptorServiceImpl implements receptorService {
    @Resource
    private prescription_listMapper prescription_listMapper;
    @Resource
    private stockMapper stockMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Resource
    private fdaMapper fdaMapper;
    @Resource
    private departmentMapper departmentMapper;
    @Resource
    private patientMapper patientMapper;
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

    @Override
    public Map<String, Object> unpaidOrders(Long merdicare) {
        patient patient=patientMapper.selectByPrimaryKey(merdicare);
        merdicare=patient.getMedicare();
        Map<String,List<OrderResponse>>orders=new HashMap<>();
        List<OrderResponse>orderResponses=new ArrayList<>();
        OrderResponse receptorItem=new OrderResponse();
        OrderResponse departmentItem=new OrderResponse();
        Detail receptorDetial=new Detail();
        receptorItem.setDetails(receptorDetial);
        List<Item>receptorList=new ArrayList<>();
        List<Item>departmentItems=new ArrayList<>();
        receptor r=receptorMapper.selectByMid(merdicare);
        List<prescription_list> prescription_lists=prescription_listMapper.findByRid(r.getId());
        String date= DateUtil.timeStamp2Date(r.getCreatetime()+"","yyyy-MM-dd HH:mm:ss");
        receptorItem.setDate(date);
        departmentItem.setDate(date);
        receptorItem.setOid(r.getId().intValue());
        departmentItem.setOid(r.getId().intValue());
        double total=0;
        if (prescription_lists!=null){
            for (prescription_list p:prescription_lists
                 ) {

                Item item=new Item();
                fda f=fdaMapper.selectByPrimaryKey(p.getFid().intValue());
                item.setName(f.getDrug());
                item.setNum(p.getNum().intValue());
                item.setPrice(f.getPrice()*p.getNum().intValue());
                receptorList.add(item);
                total+=p.getNum()*f.getPrice();
            }
            receptorDetial.setItems(receptorList);
        }
        if(total!=0){
            receptorItem.setDetails(receptorDetial);
            receptorDetial.setTotal(total*0.5);
            orderResponses.add(receptorItem);
        }
        total=0;
        Detail departmentDetail=new Detail();
        List<department> departmentList=departmentMapper.findByRid(r.getId().intValue());
        if (departmentList!=null){
            for (department d:departmentList
                 ) {
                Item item=new Item();
                item.setName(d.getDepartment());
                System.out.println(d.getId());
                item.setPrice(d.getPrice());
                item.setNum(1);
                departmentItems.add(item);
                total+=d.getPrice();
            }
            departmentDetail.setItems(departmentItems);
        }
        departmentDetail.setTotal(Double.valueOf(departmentList.size()));
        if(total!=0){
            departmentItem.setDetails(departmentDetail);
            departmentDetail.setTotal(total*0.5);
            orderResponses.add(departmentItem);
        }

        Map<String,Object>order=new HashMap<>();
        order.put("order",orderResponses);
        return order;
    }
}

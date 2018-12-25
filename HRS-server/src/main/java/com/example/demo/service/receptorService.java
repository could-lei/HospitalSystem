package com.example.demo.service;

import com.example.demo.model.receptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/11/30.
 */
public interface receptorService {
    public List<receptor> getReceptors(Integer id);
    public void addReceptor();
//    public void addPrescription_list(prescription);
    public double account(Long medicare);
    public void charge(Long medicare);
    public Map<String,Object> unpaidOrders(Long merdicare);
}

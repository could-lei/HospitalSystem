package com.example.demo.service;

import com.example.demo.enums.TestOrderResponse;

import java.util.List;

/**
 * Created by next on 2018/12/16.
 */
public interface checkService{
    public void finishCheck(Long medicareId);
    public List<TestOrderResponse> FindTest(Long type,Long medicareId);
}

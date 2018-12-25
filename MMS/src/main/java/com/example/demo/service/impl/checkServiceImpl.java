package com.example.demo.service.impl;

import com.example.demo.enums.TestOrderResponse;
import com.example.demo.mapper.checkMapper;
import com.example.demo.mapper.patientMapper;
import com.example.demo.mapper.receptorMapper;
import com.example.demo.model.check;
import com.example.demo.model.department;
import com.example.demo.model.patient;
import com.example.demo.model.receptor;
import com.example.demo.service.checkService;
import com.example.demo.service.departmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by next on 2018/12/16.
 */
@Service
public class checkServiceImpl implements checkService {
    @Resource
    private checkMapper checkMapper;
    @Resource
    private patientMapper patientMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Autowired
    private departmentService departmentService;
    @Override
    public void finishCheck(Long medicareId) {
//        receptor r=receptorMapper.selectByPrimaryKey(medicareId.intValue());
//        patient x= patientMapper.selectByMedicareId(medicareId);
        checkMapper.updateById(medicareId);
//        check.setStatus(1);
//        checkMapper.finishCheck(r.getId());
//        checkMapper.updateByPrimaryKey(check);
    }

    @Override
    public List<TestOrderResponse> FindTest(Long type, Long medicareId) {
        List<check>checks=checkMapper.selectByMid(medicareId);
        List<TestOrderResponse> testOrderResponses=new ArrayList<>();
        for (check c:checks
             ) {
            TestOrderResponse testOrderResponse=new TestOrderResponse();
            testOrderResponse.setOid(c.getId().intValue());
            department d=departmentService.search_department(c.getdId().intValue());
            testOrderResponse.setType(d.getDepartment());
            Long rId=c.getrId();
            receptor r=receptorMapper.selectByPrimaryKey(rId.intValue());
            patient p=patientMapper.selectByMedicareId(medicareId);
            testOrderResponse.setName(p.getName());
            testOrderResponses.add(testOrderResponse);
        }
        return testOrderResponses;
    }
}

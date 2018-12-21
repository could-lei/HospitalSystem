package com.example.demo.service.impl;

import com.example.demo.mapper.checkMapper;
import com.example.demo.mapper.departmentMapper;
import com.example.demo.mapper.patientMapper;
import com.example.demo.mapper.receptorMapper;
import com.example.demo.model.check;
import com.example.demo.model.department;
import com.example.demo.model.patient;
import com.example.demo.model.receptor;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.departmentService;
import com.example.demo.unitl.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by next on 2018/11/30.
 */
@Service
public class departmentServiceImpl implements departmentService {
    @Resource
    private departmentMapper departmentMapper;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private patientMapper patientMapper;
    @Resource
    private checkMapper checkMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Override
    public List<department> get_departments() {
        return departmentMapper.selectAll();
    }

    @Override
    public department search_department(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void applyCheck(List<Long> ids, Long medicareId,int eId) {
        patient x=patientMapper.selectByPrimaryKey(medicareId);
        receptor r=receptorMapper.selectByUid(medicareId);
        if (r==null){
            receptor receptor=new receptor();
            receptor.setUid(medicareId);
            receptor.setEid(Long.valueOf(eId));
            receptor.setCreatetime(Integer.valueOf(DateUtil.timeStamp()));
            receptor.setReceptor(" ");
            receptorMapper.insert(receptor);
            r=receptorMapper.selectByUid(medicareId);
        }
        for (Long id:ids
             ) {
            department d=departmentMapper.selectByPrimaryKey(id.intValue()+1);
            System.out.println("test id :"+d.getDepartment());
            String name=d.getDepartment();
            LinkedList<patient> patients=appointmentService.save(name);
            patient p=patientMapper.selectByMedicareId(medicareId);
            patients.addFirst(p);
            check k=new check();
            k.setrId(r.getId());
            k.setdId(d.getId().longValue());
//            k.set
            checkMapper.insert(k);
            appointmentService.update(name,patients);
        }

    }

    @Override
    public List<String> getProject(Long medicareId) {
        List<String>strings=new ArrayList<>();
        List<check>checks=checkMapper.selectByMid(medicareId);
        for (check c:checks
             ) {
            department d=departmentMapper.selectByPrimaryKey(c.getdId().intValue());
            strings.add(d.getDepartment());
        }
        return strings;
    }
}

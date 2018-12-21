package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entry.SysUser;
import com.example.demo.Repository.UserRepository;
import com.example.demo.enums.PrescriptionOrderDetail;
import com.example.demo.enums.PrescriptionOrderResponse;
import com.example.demo.enums.PrescriptionRequest;
import com.example.demo.enums.fda_lis;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.departmentService;
import com.example.demo.service.prescriptionService;
import com.example.demo.unitl.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by next on 2018/12/3.
 */
@Service
public class prescriptionServiceImpl implements prescriptionService {
    @Resource
    private prescription_listMapper prescription_listMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Resource
    private patientMapper patientMapper;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private departmentService departmentService;
    @Autowired
    private UserRepository userRepository;
    @Resource
    private stockMapper stockMapper;
    @Autowired
    private userMapper userMapper;
    @Autowired
    private fdaMapper fdaMapper;
    @Override
    public void prescription(Long medicareId, Long eId, List<PrescriptionRequest> fda_list) {
        try {
            patient x=patientMapper.selectByPrimaryKey(medicareId);
            if (x==null){
                return;
            }
            user sysUser=userMapper.selectById(eId);
            Integer dId=Integer.valueOf(sysUser.getEid().intValue());
            department department=departmentService.search_department(dId);
            LinkedList<patient> linkedList=appointmentService.save(department.getDepartment());
            receptor receptor=new receptor();
            receptor.setCreatetime(Integer.valueOf(DateUtil.timeStamp()));
            receptor.setEid(eId);
            receptor.setReceptor(JSON.toJSONString(fda_list));
            receptor.setUid(x.getId());
            receptorMapper.closeReceptor(x.getId());
            receptorMapper.insert(receptor);
            receptor=receptorMapper.selectByMedicareId(x.getMedicare());
            for (PrescriptionRequest f:fda_list
                    ) {
                prescription_list p=new prescription_list();
                p.setFid(Long.valueOf(f.getId()));
                p.setNum(f.getNum());
                p.setRid(receptor.getId());
                prescription_listMapper.insert(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean finishPrescription(Long medicareId) {
//        patient x=patientMapper.selectByMedicareId(medicareId);
//        receptor receptor=receptorMapper.selectByMedicareId(medicareId);
//        System.out.println(JSON.toJSONString(receptor));
        List<prescription_list>lists=prescription_listMapper.selectByRid(medicareId);
        Boolean falg=true;
        for (prescription_list p:lists
             ) {
            stock stock=stockMapper.selectByPrimaryKey(p.getFid().intValue());
            if(stock.getNumber()-p.getNum()<0){
                falg=false;
            }
        }
        return falg;
    }

    @Override
    public Boolean finishDispensary(Long m) {
//        patient x=patientMapper.selectByMedicareId(m);
//        receptor receptor=receptorMapper.selectByMedicareId(m);
        List<prescription_list>lists=prescription_listMapper.selectByRid(m);
        List<stock> stocks=new LinkedList<>();
        Boolean falg=true;
        for (prescription_list p:lists
             ) {
            stock stock=stockMapper.selectByPrimaryKey(p.getFid().intValue());
            if(stock.getNumber()-p.getNum()<0){
                falg=false;
                return false;
            }
            stock.setNumber((int) (stock.getNumber()-p.getNum()));
            stocks.add(stock);
        }
        for (stock s:stocks
             ) {
            try {
                stockMapper.updateByPrimaryKey(s);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        receptorMapper.finishReceptor(m);
        return falg;
    }

    @Override
    public PrescriptionOrderResponse SearchPrescription(Long medicareId) {
        patient x=patientMapper.selectByMedicareId(medicareId);
        PrescriptionOrderResponse p=new PrescriptionOrderResponse();
        receptor r=receptorMapper.selectByMedicareId(medicareId);
        if (r==null){
            return null;
        }
        p.setOid(r.getId().toString());
        String date=DateUtil.timeStamp2Date(r.getCreatetime()+"","yyyy-MM-dd HH:mm:ss");
        p.setDate(date);
        p.setName(x.getName());
        return p;
    }

    @Override
    public List<PrescriptionOrderDetail> PRESCRIPTION_ORDER_DETAILS(Long orderId) {
        List<prescription_list>prescription_lists=prescription_listMapper.selectByRid(orderId);
        List<PrescriptionOrderDetail>prescriptionOrderDetails=new ArrayList<>();
        for (prescription_list p:prescription_lists
             ) {
            PrescriptionOrderDetail prescriptionOrderDetail=new PrescriptionOrderDetail();
            prescriptionOrderDetail.setNum(p.getNum());
            fda fda=fdaMapper.selectByPrimaryKey(p.getFid().intValue());
            prescriptionOrderDetail.setName(fda.getDrug());
            prescriptionOrderDetails.add(prescriptionOrderDetail);
        }
        return prescriptionOrderDetails;
    }
}

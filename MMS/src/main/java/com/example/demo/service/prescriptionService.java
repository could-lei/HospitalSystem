package com.example.demo.service;

import com.example.demo.enums.PrescriptionOrderDetail;
import com.example.demo.enums.PrescriptionOrderResponse;
import com.example.demo.enums.PrescriptionRequest;
import com.example.demo.enums.fda_lis;

import java.util.List;

/**
 * Created by next on 2018/12/3.
 */
public interface prescriptionService {
    public void prescription(Long medicareId,Long eId,List<PrescriptionRequest> fda_list);
    public Boolean finishPrescription(Long medicareId);
    public Boolean finishDispensary(Long m);
    public PrescriptionOrderResponse SearchPrescription(Long medicareId);
    public List<PrescriptionOrderDetail> PRESCRIPTION_ORDER_DETAILS(Long orderId);
}

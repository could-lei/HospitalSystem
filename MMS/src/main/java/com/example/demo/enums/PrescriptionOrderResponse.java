package com.example.demo.enums;

/**
 * Created by next on 2018/12/20.
 * @author next
 */
public class PrescriptionOrderResponse {
    private String oid;
    private String date;
    private String name;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

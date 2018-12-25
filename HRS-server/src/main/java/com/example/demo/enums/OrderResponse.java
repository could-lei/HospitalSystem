package com.example.demo.enums;

/**
 * Created by next on 2018/12/23.
 * @author next
 */
public class OrderResponse {
    private int oid;
    private String date;
    private  Detail details;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Detail getDetails() {
        return details;
    }

    public void setDetails(Detail details) {
        this.details = details;
    }
}

package com.example.demo.model;

public class fda {
    private Integer id;

    private String drug;

    private String drugnumber;

    private String describe;

    private String pruductid;

    private Double dosage;

    private Double price;

    private Integer effectivedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug == null ? null : drug.trim();
    }

    public String getDrugnumber() {
        return drugnumber;
    }

    public void setDrugnumber(String drugnumber) {
        this.drugnumber = drugnumber == null ? null : drugnumber.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getPruductid() {
        return pruductid;
    }

    public void setPruductid(String pruductid) {
        this.pruductid = pruductid == null ? null : pruductid.trim();
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public Integer getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Integer effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
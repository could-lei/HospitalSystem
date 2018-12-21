package com.example.demo.model;

public class stock {
    private Integer id;

    private Integer fdaid;

    private Integer groundTime;

    private Integer number;

    private String unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFdaid() {
        return fdaid;
    }

    public void setFdaid(Integer fdaid) {
        this.fdaid = fdaid;
    }

    public Integer getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(Integer groundTime) {
        this.groundTime = groundTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}
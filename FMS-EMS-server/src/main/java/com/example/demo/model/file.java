package com.example.demo.model;

public class file {
    private Integer id;

    private Integer uid;

    private Integer reachtime;

    private Integer leavetime;

    private String comment;

    private Integer receptorid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReachtime() {
        return reachtime;
    }

    public void setReachtime(Integer reachtime) {
        this.reachtime = reachtime;
    }

    public Integer getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Integer leavetime) {
        this.leavetime = leavetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getReceptorid() {
        return receptorid;
    }

    public void setReceptorid(Integer receptorid) {
        this.receptorid = receptorid;
    }
}
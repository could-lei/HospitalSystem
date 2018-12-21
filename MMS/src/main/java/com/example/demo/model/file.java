package com.example.demo.model;

public class file {
    private Integer id;

    private Long uid;

    private Integer reachtime;

    private Integer leavetime;

    private String comment;

    private Long receptorid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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

    public Long getReceptorid() {
        return receptorid;
    }

    public void setReceptorid(Long receptorid) {
        this.receptorid = receptorid;
    }
}
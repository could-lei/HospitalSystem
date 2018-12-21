package com.example.demo.model;

public class dictionnarys {
    private Integer id;

    private Integer no;

    private String content;

    private String define;

    private Long date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define == null ? null : define.trim();
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
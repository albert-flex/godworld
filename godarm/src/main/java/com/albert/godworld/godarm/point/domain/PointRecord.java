package com.albert.godworld.godarm.point.domain;

import java.util.Date;

public class PointRecord {

    private Long id;
    private Long userId;
    private Long outCome;
    private String type;
    private String detail;
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOutCome() {
        return outCome;
    }

    public void setOutCome(Long outCome) {
        this.outCome = outCome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

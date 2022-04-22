package com.albert.godworld.godarm.social.domain;

import java.util.Date;

public class SocialActivity {
    public enum State{
        UN_STARTED,
        UNDER_GOING,
        ENDED,
    }

    private Long id;
    private Long socialId;
    private Long hockMemberId;
    private Long honerMemberId;
    private Long contactMemberId;

    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long socialId) {
        this.socialId = socialId;
    }

    public Long getHockMemberId() {
        return hockMemberId;
    }

    public void setHockMemberId(Long hockMemberId) {
        this.hockMemberId = hockMemberId;
    }

    public Long getHonerMemberId() {
        return honerMemberId;
    }

    public void setHonerMemberId(Long honerMemberId) {
        this.honerMemberId = honerMemberId;
    }

    public Long getContactMemberId() {
        return contactMemberId;
    }

    public void setContactMemberId(Long contactMemberId) {
        this.contactMemberId = contactMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

package com.albert.godworld.godarm.social.domain;

import java.util.Date;

public class SocialInfo {
    public enum State {
        LOCKED, NORMAL
    }

    private Long id;
    private Long masterAuthorId;
    private Long memberNumber;
    private Long topActivityId;
    private State state;

    private String name;
    private String description;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMasterAuthorId() {
        return masterAuthorId;
    }

    public void setMasterAuthorId(Long masterAuthorId) {
        this.masterAuthorId = masterAuthorId;
    }

    public Long getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Long memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Long getTopActivityId() {
        return topActivityId;
    }

    public void setTopActivityId(Long topActivityId) {
        this.topActivityId = topActivityId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

package com.albert.godworld.godarm.notify.domain;

import java.util.Date;

public class NotifyInfo {

    public enum Type{
        ADMIN, READER, AUTHOR, SOCIAL,
        BOOK, POINT
    }

    private Long id;
    private Long targetId;
    private Type targetType;
    private Long sourceId;
    private Type sourceType;
    private String content;
    private Date time;
    private Boolean read;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Type getTargetType() {
        return targetType;
    }

    public void setTargetType(Type targetType) {
        this.targetType = targetType;
    }

    public Type getSourceType() {
        return sourceType;
    }

    public void setSourceType(Type sourceType) {
        this.sourceType = sourceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}

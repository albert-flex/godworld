package com.albert.godworld.godarm.book.domain;

import java.util.Date;

public class BookInfo {

    private Long id;
    private Long authorId;
    private Long clickCount;
    private Long boardId;
    private String title;
    private String description;
    private Boolean upShelf;
    private Date createTime;
    private Date updateTime;
    private String updateTitle;
    private Long newChapterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getUpShelf() {
        return upShelf;
    }

    public void setUpShelf(Boolean upShelf) {
        this.upShelf = upShelf;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public Long getNewChapterId() {
        return newChapterId;
    }

    public void setNewChapterId(Long newChapterId) {
        this.newChapterId = newChapterId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }
}

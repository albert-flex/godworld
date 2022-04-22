package com.albert.godworld.godarm.book.domain;

import java.util.Date;

public class BookHot {

    private Long id;
    private Date hotDay;
    private Integer order;
    private Long hotScore;

    private Long bookId;
    private Long authorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHotDay() {
        return hotDay;
    }

    public void setHotDay(Date hotDay) {
        this.hotDay = hotDay;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getHotScore() {
        return hotScore;
    }

    public void setHotScore(Long hotScore) {
        this.hotScore = hotScore;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
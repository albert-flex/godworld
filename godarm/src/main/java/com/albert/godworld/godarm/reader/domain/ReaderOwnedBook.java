package com.albert.godworld.godarm.reader.domain;

import java.util.Date;

public class ReaderOwnedBook {

    private Long readerId;
    private Long bookId;
    private Date ownTime;

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getOwnTime() {
        return ownTime;
    }

    public void setOwnTime(Date ownTime) {
        this.ownTime = ownTime;
    }
}

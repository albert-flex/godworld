package com.albert.godworld.godarm.reader.domain;

import java.util.Date;

public class ReaderStarredBook {

    private Long readerId;
    private Long bookId;
    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookChapter {

    private Long id;
    private Long bookId;
    private Long volumeId;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Long prevChapterId;
    private Long nextChapterId;
}

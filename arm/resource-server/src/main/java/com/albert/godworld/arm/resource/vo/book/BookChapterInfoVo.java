package com.albert.godworld.arm.resource.vo.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookChapterInfoVo{
    private Long id;
    private Long bookId;
    private String bookName;
    private Long volumeId;
    private String volumeTitle;
    private String title;
    private Date updateTime;
    private String content;
    private Long prevChapterId;
    private Long nextChapterId;
}

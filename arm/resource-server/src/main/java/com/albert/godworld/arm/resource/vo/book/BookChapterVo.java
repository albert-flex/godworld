package com.albert.godworld.arm.resource.vo.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookChapterVo {

    private Long id;
    private Long bookId;
    private String title;
    private Date updateTime;
    private String volumeTitle;
    private Long volumeId;
    private Long prev_chapter_id;
    private Long next_chapter_id;
}

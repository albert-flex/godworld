package com.albert.godworld.arm.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookChapter {

    private Long id;
    private Long bookId;
    private Long cataId;
    private String title;
    private String content;
    private Date createTime;
}

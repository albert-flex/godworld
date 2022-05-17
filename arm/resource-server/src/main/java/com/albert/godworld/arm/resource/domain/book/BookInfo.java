package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookInfo {

    private Long id;
    private Long authorId;
    private String name;
    private String description;
    private Long wordCount;
    private Long boardId;
    private Long point;
    private Date createTime;
    private Date updateTime;
}

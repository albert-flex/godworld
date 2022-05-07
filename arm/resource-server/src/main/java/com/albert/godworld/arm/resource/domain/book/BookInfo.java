package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookInfo {

    private Long id;
    private String authorName;
    private String name;
    private String boardName;
    private Date createTime;
    private Date updateTime;
}
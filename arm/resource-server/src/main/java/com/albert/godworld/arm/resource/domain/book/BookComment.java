package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookComment {

    private Long id;
    private Long bookId;
    private Long userId;
    private String content;
    private Date createTime;
}

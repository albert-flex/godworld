package com.albert.godworld.arm.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookComment {

    private Long id;
    private Long bookId;
    private String readerName;
    private String content;
    private Date createTime;
}

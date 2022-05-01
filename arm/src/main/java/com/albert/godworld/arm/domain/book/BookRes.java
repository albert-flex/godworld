package com.albert.godworld.arm.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookRes {

    private Long id;
    private Long bookId;
    private Date createTime;
    private String title;
    private Long resId;
}

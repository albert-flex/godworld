package com.albert.godworld.arm.domain.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookShelfBook {

    private Long id;
    private Long userId;
    private Long bookId;
    private Date addTime;
}

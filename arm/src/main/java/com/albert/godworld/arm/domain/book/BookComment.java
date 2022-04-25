package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookComment {

    @TableId
    private Long id;
    private Long bookId;
    private Long readerId;
    private Boolean favor;//Book
    private String content;
    private Date time;
    private Long like;//Reader
}

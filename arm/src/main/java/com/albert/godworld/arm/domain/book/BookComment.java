package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookComment {

    @TableId
    private Long id;
    private Long readerId;
    private String content;
    private Date time;
    private Long like;
}

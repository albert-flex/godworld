package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookChapter {

    @TableId
    private Long id;
    private Long bookId;
    private Long partId;
    private Long volumeId;
    private String name;
    private Date createTime;
    private Date updateTime;
}

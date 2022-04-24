package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data

public class BookResource {

    @TableId
    private Long id;
    private Long bookId;
    private Long chapterId;
    private Long resId;
    private Date publishTime;
}

package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookHot {

    @TableId
    private Long id;
    private Date hotDay;
    private Integer order;
    private Long hotScore;

    private Long bookId;
    private Long authorId;
}

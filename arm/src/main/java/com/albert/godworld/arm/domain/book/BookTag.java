package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookTag {

    @TableId
    private Long id;
    private String name;
    private String detail;
    private Date publishTime;
}

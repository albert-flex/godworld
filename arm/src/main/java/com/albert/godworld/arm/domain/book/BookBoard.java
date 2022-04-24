package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BookBoard {

    @TableId
    private Long id;
    private String name;
    private String description;
}

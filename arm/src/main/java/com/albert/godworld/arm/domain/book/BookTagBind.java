package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BookTagBind {

    @TableId
    private Long id;
    private Long bookId;
    private Long tagId;
}

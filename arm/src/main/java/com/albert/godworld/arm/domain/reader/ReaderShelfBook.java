package com.albert.godworld.arm.domain.reader;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ReaderShelfBook {

    @TableId
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer order;
}

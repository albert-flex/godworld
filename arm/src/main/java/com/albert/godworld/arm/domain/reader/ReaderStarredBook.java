package com.albert.godworld.arm.domain.reader;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ReaderStarredBook {

    @TableId
    private Long id;
    private Long readerId;
    private Long bookId;
    private Date time;
}

package com.albert.godworld.arm.domain.reader;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ReaderReadRecord {

    @TableId
    private Long id;
    private Long readerId;
    private Long bookId;
    private Long chapterId;
    private Date time;
}

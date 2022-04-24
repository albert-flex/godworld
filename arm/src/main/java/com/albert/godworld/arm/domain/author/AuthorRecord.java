package com.albert.godworld.arm.domain.author;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorRecord {

    @TableId
    private Long id;
    private Long authorId;
    private AuthorRecordType type;
    private String description;
    private Date time;
}

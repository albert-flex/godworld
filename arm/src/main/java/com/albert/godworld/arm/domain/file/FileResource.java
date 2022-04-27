package com.albert.godworld.arm.domain.file;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class FileResource {

    @TableId
    private Long id;
    private String format;

    private String lib;
    private String name;
}

package com.albert.godworld.arm.resource.vo.social;

import lombok.Data;

import java.util.Date;

@Data
public class AnnounceVo {

    private Long id;
    private Long authorId;
    private String authorName;
    private String title;
    private String content;
    private Date publishTime;
}

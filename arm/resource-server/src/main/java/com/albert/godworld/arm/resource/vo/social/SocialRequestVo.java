package com.albert.godworld.arm.resource.vo.social;

import lombok.Data;

import java.util.Date;

@Data
public class SocialRequestVo {

    private Long id;
    private Long socialId;
    private Long authorId;
    private String authorName;
    private Integer type;
    private String message;
    private String content;
    private Date createTime;
}

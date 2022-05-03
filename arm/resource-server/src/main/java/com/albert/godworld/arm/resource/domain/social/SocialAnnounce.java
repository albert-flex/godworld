package com.albert.godworld.arm.resource.domain.social;

import lombok.Data;

import java.util.Date;

@Data
public class SocialAnnounce {

    private Long id;
    private Long socialId;
    private Long publishAuthorId;
    private String title;
    private String content;
    private Date publishTime;
    private Date updateTime;
}

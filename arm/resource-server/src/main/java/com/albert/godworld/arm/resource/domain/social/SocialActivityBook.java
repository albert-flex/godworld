package com.albert.godworld.arm.resource.domain.social;

import lombok.Data;

import java.util.Date;

@Data
public class SocialActivityBook {

    private Long id;
    private Long socialId;
    private Long activityId;
    private Long bookId;
}

package com.albert.godworld.arm.resource.domain.social;

import lombok.Data;

import java.util.Date;

@Data
public class SocialActivity {

    private Long id;
    private Long socialId;
    private Long adminMemberId;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private Boolean actOn;
}

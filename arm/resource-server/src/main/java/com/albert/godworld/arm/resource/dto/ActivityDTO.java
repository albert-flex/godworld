package com.albert.godworld.arm.resource.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {

    private Long socialId;
    private Long adminMemberId;
    private String name;
    private Date startTime;
    private Date endTime;
    private String description;
}

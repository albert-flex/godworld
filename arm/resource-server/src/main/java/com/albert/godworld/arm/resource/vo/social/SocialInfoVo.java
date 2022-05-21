package com.albert.godworld.arm.resource.vo.social;

import lombok.Data;

import java.util.Date;

@Data
public class SocialInfoVo {
    private Long id;
    private String name;
    private Long masterId;
    private String masterName;
    private Date createTime;
    private String newestAct;
    private String newestAnnounce;
    private String moto;
}

package com.albert.godworld.arm.domain.social;

import lombok.Data;

import java.util.Date;

@Data
public class SocialInfo {

    private Long id;
    private String name;
    private Long masterId;
    private String moto;
    private Date createTime;
}

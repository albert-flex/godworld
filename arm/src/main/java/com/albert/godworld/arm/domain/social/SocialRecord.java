package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialRecord {

    @TableId
    private Long id;
    private Long socialId;
    private SocialRequestType type;
    private String content;
    private Date time;
}

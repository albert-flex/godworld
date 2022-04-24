package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialInfo {

    @TableId
    private Long id;
    private Long masterAuthorId;
    private Long memberNumber;
    private Long topActivityId;
    private Boolean locked;
    private String name;
    private String description;
    private Date createTime;
}

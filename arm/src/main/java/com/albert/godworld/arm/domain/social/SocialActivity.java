package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialActivity {

    @TableId
    private Long id;
    private Long socialId;
    private Long hockMemberId;
    private Long honerMemberId;
    private Long contactMemberId;

    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private SocialActivityState state;
}

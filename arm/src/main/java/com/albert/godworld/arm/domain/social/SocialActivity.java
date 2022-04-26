package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialActivity {

    @TableId
    private Long id;
    private Long socialId;
    private Long honerMemberId;//主办
    private Long contactMemberId;//联系人

    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private SocialActivityState state;
}

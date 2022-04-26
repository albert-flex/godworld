package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialRequest {

    @TableId
    private Long id;
    private String content;
    private Long socialId;
    private SocialRequestType type;
    private Long requestAuthorId;
    private Long destId;
    private SocialRequestState state;
    private Long handlerId;
    private Date time;
    private Date handledTime;
}

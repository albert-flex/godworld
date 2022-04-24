package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SocialRequest {

    @TableId
    private Long id;
    private String content;
    private SocialRequestType type;
    private Long requestId;
    private SocialRequestState state;
    private Long handlerId;
}

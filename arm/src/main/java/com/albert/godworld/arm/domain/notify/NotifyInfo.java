package com.albert.godworld.arm.domain.notify;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class NotifyInfo {

    @TableId
    private Long id;
    private NotifyType source;
    private Long sourceId;
    private NotifyType target;
    private Long targetId;
    private String content;
    private Date time;
    private Boolean read;
}

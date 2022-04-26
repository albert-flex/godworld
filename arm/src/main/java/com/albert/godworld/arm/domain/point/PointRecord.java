package com.albert.godworld.arm.domain.point;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class PointRecord {

    @TableId
    private Long id;
    private Long sourceId;
    private Long targetId;
    private Long outCome;
    private Long orderId;
    private PointOrderType type;
    private String detail;
    private Date time;
}

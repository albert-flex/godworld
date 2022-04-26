package com.albert.godworld.arm.domain.point;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class PointOrder {

    @TableId
    private Long id;
    private Long sourceId;
    private Long targetId;
    private Long orderScore;

    private PointOrderType type;
    private PointOrderState state;
    private String back;
    private String failedWord;
    private Date createTime;
    private Date handleTime;
}

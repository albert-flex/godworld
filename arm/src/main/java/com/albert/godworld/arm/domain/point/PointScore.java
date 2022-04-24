package com.albert.godworld.arm.domain.point;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PointScore {

    @TableId
    private Long id;
    private Long userId;
    private Long point;
}

package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialHot {

    @TableId
    private Long id;
    private Date hotDay;
    private Integer order;
    private Long hotScore;
    private Long socialId;
}

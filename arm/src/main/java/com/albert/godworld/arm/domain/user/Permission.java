package com.albert.godworld.arm.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Permission {

    @TableId
    private Long id;
    private String name;
    private String description;
}

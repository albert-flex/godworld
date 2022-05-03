package com.albert.godworld.arm.resource.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UGroup {

    @TableId
    private Long id;
    private String name;
    private String description;
    
}

package com.albert.godworld.arm.resource.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserGroup {

    @TableId
    private Long id;
    private Long userId;
    private Long groupId;
}

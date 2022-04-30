package com.albert.godworld.arm.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class GroupPermission {

    @TableId
    private Long id;
    private Long groupId;
    private Long permissionId;
}

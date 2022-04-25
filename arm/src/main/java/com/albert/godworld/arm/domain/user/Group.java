package com.albert.godworld.arm.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Group {

    @TableId
    private Long id;
    private String name;
    private String description;

    private List<Permission> permissions;
}

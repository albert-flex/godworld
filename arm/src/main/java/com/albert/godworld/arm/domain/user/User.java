package com.albert.godworld.arm.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId
    private Long id;
    private String userName;
    private String password;
    private Boolean enabled;
    private Boolean login;
    private Date createTime;
    private Date lastLoginTime;

    private Group group;
}

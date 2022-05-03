package com.albert.godworld.arm.resource.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Permission implements GrantedAuthority {

    @TableId
    private Long id;
    private String name;
    private String description;

    @Override
    public String getAuthority() {
        return name;
    }
}

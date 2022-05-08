package com.albert.godworld.arm.resource.domain.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class User implements UserDetails {

    @TableId
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean locked;
    private Boolean login;

    private Date createTime;
    private Date lastLoginTime;

    @TableField(exist = false)
    private List<Permission> allPermission;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return allPermission;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !locked;
    }
}

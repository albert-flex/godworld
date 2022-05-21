package com.albert.godworld.arm.authorize.service;

import com.albert.godworld.arm.authorize.domain.Permission;
import com.albert.godworld.arm.authorize.domain.User;
import com.albert.godworld.arm.authorize.mapper.PermissionMapper;
import com.albert.godworld.arm.authorize.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceSPI extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public UserServiceSPI(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, name).last("limit 1");
        return super.getOne(queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getByName(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        List<Permission> permissions=permissionMapper.allPerOfUser(user.getId());
        user.setAllPermission(permissions);
        return user;
    }
}


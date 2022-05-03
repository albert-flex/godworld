package com.albert.godworld.arm.resource.service.user.impl;

import com.albert.godworld.arm.resource.domain.user.Permission;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.mapper.user.UserMapper;
import com.albert.godworld.arm.resource.service.user.PermissionService;
import com.albert.godworld.arm.resource.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceSPI extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final PermissionService permissionService;

    @Autowired
    public UserServiceSPI(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Page<User> pageOf(Page<User> page, String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUsername, name);
        return super.page(page, queryWrapper);
    }

    @Override
    public boolean checkNameAvail(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, name);
        return super.getOne(queryWrapper) == null;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, name);
        return super.getOne(queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getByName(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        List<Permission> permissions=permissionService.listOfUser(user.getId());
        user.setAllPermission(permissions);
        return user;
    }
}


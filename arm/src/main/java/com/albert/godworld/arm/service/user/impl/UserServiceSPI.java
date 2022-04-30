package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.mapper.user.UserMapper;
import com.albert.godworld.arm.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceSPI extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public Page<User> pageOf(Page<User> page, String name) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUserName,name);
        return super.page(page,queryWrapper);
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,name);
        return super.getOne(queryWrapper);
    }
}


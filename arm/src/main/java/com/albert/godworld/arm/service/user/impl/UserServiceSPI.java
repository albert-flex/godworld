package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.mapper.user.UserMapper;
import com.albert.godworld.arm.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceSPI extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public User loadFromUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);

        return super.getOne(queryWrapper);
    }

    @Override
    public Page<User> pageOf(Page<User> page, String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", userName);

        return super.page(page, queryWrapper);
    }

    private String encode(String passWord) {
        return passWord;
    }

    @Override
    public boolean checkPassword(String userName, String passWord) {
        String encode = encode(passWord);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("password", encode);

        return super.getOne(queryWrapper) != null;
    }
}

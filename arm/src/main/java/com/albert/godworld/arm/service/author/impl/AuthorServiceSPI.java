package com.albert.godworld.arm.service.author.impl;

import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.albert.godworld.arm.mapper.author.AuthorMapper;
import com.albert.godworld.arm.service.author.AuthorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceSPI extends ServiceImpl<AuthorMapper, AuthorInfo>
    implements AuthorService {

    @Override
    public Page<AuthorInfo> queryByName(Page<AuthorInfo> page, String name) {
        LambdaQueryWrapper<AuthorInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(AuthorInfo::getName,name);
        return super.page(page,queryWrapper);
    }
}

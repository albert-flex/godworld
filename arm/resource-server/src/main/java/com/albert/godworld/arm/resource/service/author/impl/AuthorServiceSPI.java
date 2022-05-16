package com.albert.godworld.arm.resource.service.author.impl;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.mapper.author.AuthorMapper;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceSPI extends ServiceImpl<AuthorMapper, AuthorInfo>
    implements AuthorService {

    @Override
    public Long getAuthorIdByUserId(Long userId) {
        return baseMapper.getAuthorIdByUserId(userId);
    }

    @Override
    public boolean checkUserIdAndAuthorCorrect(Long userId, Long authorId) {
        LambdaQueryWrapper<AuthorInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthorInfo::getUserId,userId);
        queryWrapper.eq(AuthorInfo::getId,authorId);
        return super.getOne(queryWrapper)!=null;
    }

    @Override
    public Page<AuthorInfo> queryByName(Page<AuthorInfo> page, String name) {
        LambdaQueryWrapper<AuthorInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(AuthorInfo::getName,name);
        return super.page(page,queryWrapper);
    }

    @Override
    public boolean checkAuthorNameAvailable(String name) {
        LambdaQueryWrapper<AuthorInfo> queryWrapper=new LambdaQueryWrapper<>();
        return super.getOne(queryWrapper)==null;
    }
}

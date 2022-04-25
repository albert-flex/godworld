package com.albert.godworld.arm.service.author.impl;

import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.albert.godworld.arm.mapper.author.AuthorInfoMapper;
import com.albert.godworld.arm.service.author.AuthorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceSPI extends ServiceImpl<AuthorInfoMapper,AuthorInfo>
        implements AuthorService {

    @Override
    public boolean bind(Long userId, AuthorInfo info) {
        if(info.getId()!=null)return false;
        //已经有绑定的作者账号了
        AuthorInfo authorInfo=findAuthorByUserId(userId);
        if(authorInfo!=null)return false;

        info.setUserId(userId);
        return super.save(info);
    }

    @Override
    public boolean lock(Long authorId) {
        if(authorId==null)return false;

        AuthorInfo authorInfo=super.getById(authorId);
        //找不到作者，或者作者已经解除用户绑定了
        if(authorInfo==null||authorInfo.getUserId()==0)return false;

        //已经锁定了
        if(authorInfo.getLocked())return false;

        authorInfo.setLocked(true);
        return super.updateById(authorInfo);
    }

    @Override
    public boolean unLock(Long authorId) {
        if(authorId==null)return false;

        AuthorInfo authorInfo=super.getById(authorId);
        //找不到作者，或者作者已经解除用户绑定了
        if(authorInfo==null||authorInfo.getUserId()==0)return false;

        //已经解除锁定了
        if(!authorInfo.getLocked())return false;

        authorInfo.setLocked(false);
        return super.updateById(authorInfo);
    }

    @Override
    public boolean unbind(Long authorId) {
        AuthorInfo info=super.getById(authorId);
        if(info==null)return false;

        info.setUserId(0L);
        info.setLocked(true);
        return super.updateById(info);
    }

    @Override
    public AuthorInfo findAuthorByUserId(Long userId) {

        if(userId==null)return null;
        QueryWrapper<AuthorInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        return super.getOne(queryWrapper);
    }
}

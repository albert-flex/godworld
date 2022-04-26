package com.albert.godworld.arm.service.social.impl;

import com.albert.godworld.arm.domain.social.SocialMember;
import com.albert.godworld.arm.domain.social.SocialMemberType;
import com.albert.godworld.arm.mapper.social.SocialMemberMapper;
import com.albert.godworld.arm.service.social.SocialMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialMemberServiceSPI extends ServiceImpl<SocialMemberMapper, SocialMember>
    implements SocialMemberService {

    @Override
    public Page<SocialMember> pageOf(Page<SocialMember> page, Long socialId) {
        return pageOf(page,socialId,null);
    }

    @Override
    public Page<SocialMember> pageOf(Page<SocialMember> page, Long socialId,SocialMemberType type) {
        QueryWrapper<SocialMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("social_id",socialId);
        if(type!=null){
            queryWrapper.eq("type",type);
        }
        return super.page(page,queryWrapper);
    }
}

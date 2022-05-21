package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.mapper.social.SocialMemberMapper;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.albert.godworld.arm.resource.vo.social.SocialMemberSimpleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialMemberServiceSPI extends ServiceImpl<SocialMemberMapper, SocialMember>
    implements SocialMemberService {

    @Override
    public Page<SocialMemberSimpleVo> membersOf(Page<SocialMemberSimpleVo> page, Long socialId, SocialMemberType type) {
        return super.baseMapper.memberOfType(page,socialId,type);
    }

    @Override
    public Page<SocialMember> memberOfSocial(Page<SocialMember> page, Long socialId) {
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getSocialId,socialId);
        queryWrapper.orderByAsc(SocialMember::getType);
        return super.page(page,queryWrapper);
    }

    @Override
    public boolean changeType(Long memberId, SocialMemberType newType) {
        LambdaUpdateWrapper<SocialMember> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocialMember::getId,memberId);
        updateWrapper.set(SocialMember::getType,newType);
        return super.update(updateWrapper);
    }

    @Override
    public boolean changeName(Long memberId, String newMemberName) {
        LambdaUpdateWrapper<SocialMember> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocialMember::getId,memberId);
        updateWrapper.set(SocialMember::getMemberName,newMemberName);
        return super.update(updateWrapper);
    }

    @Override
    public boolean in(Long authorId,Long socialId, String name) {
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,authorId);
        SocialMember socialMember=super.getOne(queryWrapper);
        if(socialMember!=null)return false;

        SocialMember member=new SocialMember();
        member.setMemberName(name);
        member.setType(SocialMemberType.NORMAL);
        return super.save(member);
    }

    @Override
    public boolean out(Long authorId) {
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,authorId);
        return super.remove(queryWrapper);
    }

    @Override
    public Page<SocialMember> memberOfType(Page<SocialMember> page, Long socialId, SocialMemberType type) {
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getSocialId,socialId);
        queryWrapper.eq(SocialMember::getType,type);
        return super.page(page,queryWrapper);
    }
}

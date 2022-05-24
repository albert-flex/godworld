package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.domain.user.UGroups;
import com.albert.godworld.arm.resource.mapper.social.SocialMemberMapper;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.albert.godworld.arm.resource.service.user.UGroupService;
import com.albert.godworld.arm.resource.vo.social.SocialMemberSimpleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SocialMemberServiceSPI extends ServiceImpl<SocialMemberMapper, SocialMember>
        implements SocialMemberService {

    private final UGroupService uGroupService;
    private final AuthorService authorService;

    @Override
    public Page<SocialMemberSimpleVo> membersOf(Page<SocialMemberSimpleVo> page, Long socialId, SocialMemberType type) {
        return super.baseMapper.memberOfType(page, socialId, type);
    }

    @Override
    public Page<SocialMember> memberOfSocial(Page<SocialMember> page, Long socialId) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getSocialId, socialId);
        queryWrapper.orderByAsc(SocialMember::getType);
        return super.page(page, queryWrapper);
    }

    @Override
    public boolean changeType(Long memberId, SocialMemberType newType) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getId, memberId).last("limit 1");
        SocialMember member = super.getOne(queryWrapper);
        if (member == null || member.getType() == SocialMemberType.MASTER) return false;

        LambdaUpdateWrapper<SocialMember> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocialMember::getId, memberId);
        updateWrapper.set(SocialMember::getType, newType);
        boolean result = super.update(updateWrapper);
        if (!result) return false;

        AuthorInfo authorInfo = authorService.getById(member.getAuthorId());
        if (newType == SocialMemberType.ADMIN) {
            if (authorInfo == null) return false;
            return uGroupService.addToUser(authorInfo.getUserId(), UGroups.SOCIAL_ADMIN.getCode());
        } else if (newType == SocialMemberType.NORMAL) {
            if (authorInfo == null) return false;
            return uGroupService.removeFromUser(authorInfo.getUserId(), UGroups.SOCIAL_ADMIN.getCode());
        }
        return true;
    }

    @Override
    public boolean changeName(Long memberId, String newMemberName) {
        LambdaUpdateWrapper<SocialMember> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocialMember::getId, memberId);
        updateWrapper.set(SocialMember::getMemberName, newMemberName);
        return super.update(updateWrapper);
    }

    @Override
    public boolean in(Long authorId, Long socialId, String name) {
        AuthorInfo authorInfo = authorService.getById(authorId);
        if (authorInfo == null) return false;

        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, authorId).last("limit 1");
        SocialMember socialMember = super.getOne(queryWrapper);
        if (socialMember != null) return false;

        SocialMember member = new SocialMember();
        member.setMemberName(name);
        member.setType(SocialMemberType.NORMAL);

        boolean result = super.save(member);
        if (!result) return false;

        return uGroupService.addToUser(authorInfo.getUserId(), UGroups.SOCIAL.getCode());
    }

    @Override
    public boolean out(Long authorId) {
        AuthorInfo authorInfo = authorService.getById(authorId);
        if (authorInfo == null) return false;

        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, authorId);
        boolean result = super.remove(queryWrapper);
        if (!result) return false;

        result = uGroupService.removeFromUser(authorInfo.getUserId(), UGroups.SOCIAL.getCode());
        if (!result) return false;
        return uGroupService.removeFromUser(authorInfo.getUserId(), UGroups.SOCIAL_ADMIN.getCode());
    }

    @Override
    public boolean isMember(Long authorId) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, authorId).last("limit 1");

        return super.getOne(queryWrapper) != null;
    }

    @Override
    public Long memberIdOfAuthor(Long authorId) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, authorId).last("limit 1");

        SocialMember member=super.getOne(queryWrapper);
        if(member==null)return null;

        return member.getId();
    }

    @Override
    public boolean isNormal(Long authorId) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, authorId).last("limit 1");

        SocialMember member = super.getOne(queryWrapper);
        return member.getType() == SocialMemberType.NORMAL;
    }

    @Override
    public  boolean isAdmin(Long authorId){
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,authorId).last("limit 1");
        SocialMember member=super.getOne(queryWrapper);
        if(member==null)return false;

        return member.getType()==SocialMemberType.ADMIN;
    }

    @Override
    public boolean isMasterOrAdmin(Long authorId) {
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,authorId).last("limit 1");
        SocialMember member=super.getOne(queryWrapper);
        if(member==null)return false;

        return member.getType()!=SocialMemberType.NORMAL;
    }

    @Override
    public boolean isMaster(Long authorId) {
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,authorId).last("limit 1");
        SocialMember member=super.getOne(queryWrapper);
        if(member==null)return false;

        return member.getType()==SocialMemberType.MASTER;
    }

    @Override
    public boolean isMasterOrAdminByMember(Long memberId) {
        SocialMember member=super.getById(memberId);
        if(member==null)return false;

        return member.getType()!=SocialMemberType.NORMAL;
    }

    @Override
    public Page<SocialMember> memberOfType(Page<SocialMember> page, Long socialId, SocialMemberType type) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getSocialId, socialId);
        queryWrapper.eq(SocialMember::getType, type);
        return super.page(page, queryWrapper);
    }
}

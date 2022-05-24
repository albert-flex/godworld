package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.albert.godworld.arm.resource.service.social.SocialRequestHandlerFactory;
import com.albert.godworld.arm.resource.mapper.social.SocialRequestMapper;
import com.albert.godworld.arm.resource.service.social.SocialRequestService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.social.SocialRequestVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class SocialRequestServiceSPI extends ServiceImpl<SocialRequestMapper,SocialRequest> implements SocialRequestService {

    private final SocialRequestHandlerFactory factory;
    private final PrincipalConvert convert;
    private final AuthorService authorService;
    private final SocialMemberService socialMemberService;

    @Override
    public boolean isSocialAdmin(Long adminMemId, Principal principal) {
        SocialMember member=socialMemberService.getById(adminMemId);
        if(member==null||member.getType()== SocialMemberType.NORMAL)return false;

        User user=convert.convert(principal);
        Long authorId=authorService.getAuthorIdByUserId(user.getId());
        if(authorId==null)return false;

        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,authorId).last("limit 1");
        member = socialMemberService.getOne(queryWrapper);
        if(member==null||member.getType()== SocialMemberType.NORMAL)return false;

        return member.getId().equals(adminMemId);
    }

    @Override
    public Page<SocialRequestVo> page(Page<SocialRequestVo> page, Long socialId) {
        return super.baseMapper.getBySocialId(page,socialId);
    }

    @Override
    public boolean requestConfirm(Long requestId, Long adminMemberId) {
        SocialRequest request=super.getById(requestId);
        if(request==null)return false;

        return factory.confirm(request,adminMemberId);
    }

    @Override
    public boolean requestRefuse(Long requestId, Long adminMemberId) {
        SocialRequest request=super.getById(requestId);
        if(request==null)return false;

        return factory.refuse(request,adminMemberId);
    }
}

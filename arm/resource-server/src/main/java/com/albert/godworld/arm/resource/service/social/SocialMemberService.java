package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.vo.social.SocialMemberSimpleVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialMemberService extends IService<SocialMember> {

    Page<SocialMemberSimpleVo> membersOf(Page<SocialMemberSimpleVo> page, Long socialId, SocialMemberType type);

    Page<SocialMember> memberOfSocial(Page<SocialMember> page,Long socialId);
    boolean changeType(Long memberId, SocialMemberType newType);
    boolean changeName(Long memberId,String newMemberName);
    boolean in(Long authorId,Long socialId,String name);
    boolean out(Long authorId);
    Page<SocialMember> memberOfType(Page<SocialMember> page,Long socialId, SocialMemberType type);
}

package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialMember;
import com.albert.godworld.arm.domain.social.SocialMemberType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialMemberService extends IService<SocialMember> {

    Page<SocialMember> membersAll(Page<SocialMember> page);

    Page<SocialMember> memberOfSocial(Page<SocialMember> page,Long socialId);
    boolean changeType(Long memberId,SocialMemberType newType);
    boolean changeName(Long memberId,String newMemberName);
    boolean in(Long authorId,String name);
    boolean out(Long authorId);
    Page<SocialMember> memberOfType(Page<SocialMember> page,Long socialId, SocialMemberType type);
}

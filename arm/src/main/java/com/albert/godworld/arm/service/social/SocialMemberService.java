package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialMember;
import com.albert.godworld.arm.domain.social.SocialMemberType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialMemberService extends IService<SocialMember> {

    Page<SocialMember> pageOf(Page<SocialMember> page,Long socialId);
    Page<SocialMember> pageOf(Page<SocialMember> page,Long socialId, SocialMemberType type);
}

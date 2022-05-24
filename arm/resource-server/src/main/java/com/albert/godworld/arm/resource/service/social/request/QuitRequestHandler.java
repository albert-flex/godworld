package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.RequestHandler;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuitRequestHandler implements RequestHandler {

    private final SocialMemberService socialMemberService;
    private final RequestCall requestCall;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.QUIT_SOCIAL;
    }

    @Override
    public boolean okHandle(SocialRequest request, Long adminMemberId) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, request.getAuthorId()).last("limit 1");

        SocialMember member = socialMemberService.getOne(queryWrapper);
        if (member == null) {
            requestCall.setError(RVError.SOCIAL_MEMBER_ALREADY_HAS);
            return false;
        }

        if (member.getType() == SocialMemberType.MASTER) {
            requestCall.setError(RVError.SOCIAL_MEMBER_IS_MASTER);
            return false;
        } else {
            if (!socialMemberService.removeById(member.getId())) {
                requestCall.setError(RVError.DATABASE_ERROR);
                return false;
            } else {
                return true;
            }
        }
    }
}

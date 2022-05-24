package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.RequestHandler;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetAdminHandler implements RequestHandler {


    private final SocialMemberService socialMemberService;
    private final RequestCall call;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.SET_ADMIN;
    }

    @Override
    public boolean okHandle(SocialRequest request, Long adminMemberId) {
        if (socialMemberService.isMasterOrAdmin(request.getAuthorId())) {
            call.setError(RVError.SOCIAL_NOT_NORMAL);
            return false;
        }

        Long memberId = socialMemberService.memberIdOfAuthor(request.getAuthorId());
        if (!socialMemberService.changeType(memberId, SocialMemberType.NORMAL)) {
            call.setError(RVError.SOCIAL_NOT_NORMAL);
            return false;
        }

        return true;
    }
}

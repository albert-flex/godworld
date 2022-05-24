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
public class QuitAdminHandler implements RequestHandler {
    private final SocialMemberService socialMemberService;
    private final RequestCall call;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.QUIT_ADMIN;
    }

    @Override
    public boolean okHandle(SocialRequest request, Long adminMemberId) {
        if (socialMemberService.isAdmin(request.getAuthorId())) {
            Long memberId = socialMemberService.memberIdOfAuthor(request.getAuthorId());
            if (!socialMemberService.changeType(memberId, SocialMemberType.ADMIN)) {
                call.setError(RVError.DATABASE_ERROR);
                return false;
            }
            return true;
        } else {
            call.setError(RVError.SOCIAL_IS_NORMAL);
            return false;
        }
    }
}

package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SocialRequestHandlerFactory {

    private final List<RequestHandler> handlerList;
    private final SocialRequestService socialRequestService;
    private final SocialMemberService socialMemberService;

    private final RequestCall requestCall;

    private RequestHandler find(SocialRequestType type) {
        Optional<RequestHandler> optional = handlerList.stream().filter(t -> t.type() == type).findFirst();
        return optional.orElse(null);
    }

    public boolean confirm(SocialRequest request, Long adminMemberId) {
        RequestHandler handler = find(SocialRequestType.parse(request.getType()));
        if (handler == null) {
            requestCall.setError(RVError.SOCIAL_REQUEST_HANDLER_NOT_FOUND);
            return false;
        }

        if (socialMemberService.isMasterOrAdminByMember(adminMemberId)) {
            boolean result = handler.okHandle(request, adminMemberId);
            if (result) {
                return handle(request, adminMemberId, Boolean.TRUE, Boolean.TRUE);
            } else {
                requestCall.setError(RVError.SOCIAL_REQUEST_HANDLE_FAIL);
                return false;
            }
        } else {
            requestCall.setError(RVError.SOCIAL_NOT_ADMIN);
            return false;
        }
    }

    private boolean handle(SocialRequest request, Long adminMemberId, Boolean handled, Boolean confirm) {
        LambdaUpdateWrapper<SocialRequest> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocialRequest::getId, request.getId())
                .set(SocialRequest::getHandled, Boolean.TRUE)
                .set(SocialRequest::getConfirm, Boolean.FALSE)
                .set(SocialRequest::getHandleMemberId, adminMemberId);
        return socialRequestService.update(updateWrapper);
    }

    public boolean refuse(SocialRequest request, Long adminMemberId) {
        if (socialMemberService.isMasterOrAdminByMember(adminMemberId)) {
            boolean result = handle(request, adminMemberId, Boolean.TRUE, Boolean.FALSE);
            if (result) {
                return true;
            } else {
                requestCall.setError(RVError.SOCIAL_REQUEST_HANDLE_FAIL);
                return false;
            }
        } else {
            requestCall.setError(RVError.SOCIAL_NOT_ADMIN);
            return false;
        }
    }
}
